package Model;
import Controller.Authentication;
import Resource.*;
import Util.Database_connection;
import View.DisplayView;

import java.sql.*;
import java.util.ArrayList;
public class UserDAO
{

    //----------------------------------------------------------------------already exist (Receiver)
    public boolean isUserAlreadyExists(User data) throws SQLException
    {
        Database_connection db  = new Database_connection();
        try
        {
            Connection connection = db.connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT email_id FROM USER_TABLE WHERE email_id = '"+data.getEmail()+"';");
            if(rs.next())
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return false;
    }
    //--------------------------------------------------------------create new user (Receiver)
    public boolean createnewUser(User user)
    {
        Authentication obj = new Authentication();
        boolean flag = obj.checkEmailbyotp(user.getEmail(),user);
        if(flag)
        {
            Database_connection connection = new Database_connection();
            try
            {
                Connection connection1 = connection.connect();
                PreparedStatement insert = connection1.prepareStatement("INSERT INTO USER_TABLE(user_names,email_id,user_password,user_mobile,Money,Roles) VALUES(?,?,?,?,?,?)");
                insert.setString(1, user.getName());
                insert.setString(2, user.getEmail());
                insert.setString(3, user.getPassword());
                insert.setString(4, user.getPhone());
                insert.setLong(5, 0);
                insert.setString(6,"User");
                insert.executeUpdate();
                return true;
            }
            catch (SQLException e)
            {
                DisplayView.DisplayDAOError(e);
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    //--------------------------------------------------------------------------Login Access For User(Sender)
    public boolean isUserAccesstheApplication(String email,String password)
    {
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String query = "SELECT * FROM USER_TABLE WHERE email_id = ? AND user_password = ?";
            PreparedStatement ps = connection1.prepareStatement(query);
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return false;
    }
    //--------------------------------------------------------------getting of user roles
    public static String getroles(String email)
    {
        StringBuilder roles = new StringBuilder();
        Database_connection connection  = new Database_connection();
        String qquery = "SELECT Roles FROM USER_TABLE WHERE email_id = ?";
        try
        {
            Connection connection1 = connection.connect();
            PreparedStatement ps = connection1.prepareStatement(qquery);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                 roles.append(rs.getString("Roles"));
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return roles.toString();
    }
    //--------------------------------------------------------------User details for view
    public static ArrayList<ArrayList<String>> getUserDetails()
    {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            try
            {
                PreparedStatement ps = connection1.prepareStatement("SELECT user_names,email_id,user_password,user_mobile,Roles FROM USER_TABLE");
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    ArrayList<String> l = new ArrayList<>();
                    l.add(rs.getString(1));
                    l.add(rs.getString(2));
                    l.add(rs.getString(3));
                    l.add(rs.getString(4));
                    l.add(rs.getString(5));
                    list.add(l);
                }
                return list;
            }
            catch (SQLException e)
            {
                DisplayView.DisplayDAOError(e);
                list.clear();
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
            list.clear();
        }
        return list;
    }
    public  void addMoneytothewallet(long n,User obj)
    {
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String query = "UPDATE USER_TABLE SET Money = Money + ? WHERE email_id = ?";
            PreparedStatement ps = connection1.prepareStatement(query);
            ps.setLong(1,n);
            ps.setString(2,obj.getEmail());
            int  rs = ps.executeUpdate();
           DisplayView.DisplayPaymentStatus(rs);
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
    }
    //----------------------------------------------------------Modify PASSWORD
    public static boolean changeexistingpassword(User obj,String p)
    {
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String query =  "UPDATE USER_TABLE SET user_password = ? WHERE email_id = ?";
            PreparedStatement ps = connection1.prepareStatement(query);
            ps.setString(1,p);
            ps.setString(2,obj.getEmail());
            int e = ps.executeUpdate();
            if(e>=0)
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return false;
    }
    public static boolean changeexistingnumber(User obj,String p) ///-----------change mobile num
    {
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String query =  "UPDATE USER_TABLE SET user_mobile = ? WHERE email_id = ?";
            PreparedStatement ps = connection1.prepareStatement(query);
            ps.setString(1,p);
            ps.setString(2,obj.getEmail());
            int e = ps.executeUpdate();
            if(e>=0)
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return false;
    }
    public static boolean changeexistingaddress(User obj,String p) ///-----------change Home Address
    {
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String query =  "UPDATE USER_TABLE SET home_address = ? WHERE email_id = ?";
            PreparedStatement ps = connection1.prepareStatement(query);
            ps.setString(1,p);
            ps.setString(2,obj.getEmail());
            int e = ps.executeUpdate();
            if(e>=0)
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return false;
    }
    public static long getBalanceofUser(User obj) // ---------------------Get BALANCE OF USER
    {
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String q = "SELECT Money FROM USER_TABLE WHERE email_id = ?";
            PreparedStatement ps = connection1.prepareStatement(q);
            ps.setString(1,obj.getEmail());
            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    return rs.getLong("Money");
                }
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return -1;
    }
    public static boolean changepinnumber(User obj, String pin)
    {
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String query =  "UPDATE USER_TABLE SET pin_number = ? WHERE email_id = ?";
            PreparedStatement ps = connection1.prepareStatement(query);
            ps.setString(1,pin);
            ps.setString(2,obj.getEmail());
            int e = ps.executeUpdate();
            if(e>=0)
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return false;
    }
}
