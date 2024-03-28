package Model;
import Resource.Admin;
import Resource.ProductDTO;
import Util.Database_connection;
import View.DisplayView;

import java.sql.*;
import java.util.*;
public class AdminDAO
{
    public boolean isAdminaccessedtheapplication(Admin admin) throws SQLException
    {
        Database_connection db  = new Database_connection();
        try
        {
            Connection connection = db.connect();
            String query = "SELECT Admin_emailid FROM ADMIN_TABLE WHERE Admin_emailid = ? AND Admin_password = ?";
            PreparedStatement p = connection.prepareStatement(query);
            p.setString(1,admin.getEmail());
            p.setString(2,admin.getPassword());
            ResultSet rs = p.executeQuery();
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
    //-----------------------------------------------------------check role admin
    public static String getroles(String email)
    {
        StringBuilder roles = new StringBuilder();
        Database_connection connection  = new Database_connection();
        String query = "SELECT Admin_role FROM ADMIN_TABLE WHERE Admin_emailid = ?";
        try
        {
            Connection connection1 = connection.connect();
            PreparedStatement ps = connection1.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                roles.append(rs.getString("Admin_role"));
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return roles.toString();
    }
    //----------------------------------------------------------------Add new Product
    public static boolean addnewProductintable(List<ProductDTO> list)
    {
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String query = "INSERT INTO PRODUCT_TABLE(serial_number,product_name,price_product,available_quantity) VALUES(?,?,?,?)";
            PreparedStatement ps = connection1.prepareStatement(query);
            ps.setString(1,list.get(0).getSerialno());
            ps.setString(2,list.get(0).getProductname());
            ps.setLong(3,list.get(0).getPrice());
            ps.setLong(4,list.get(0).getAvailabe_quantity());
            int status = ps.executeUpdate();
            if(status!=1)
            {
                return false;
            }
            return true;
        }
        catch(SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return false;
    }
    //--------------------------------------------------------------------display all products
    public static ArrayList<ArrayList<String>> getAlltheproductdetails()
    {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        Database_connection connection  = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String query = "SELECT serial_number,product_name,price_product,available_quantity FROM PRODUCT_TABLE";
            PreparedStatement ps = connection1.prepareStatement(query);
            ResultSet rs=  ps.executeQuery();
            while(rs.next())
            {
                ArrayList<String> l = new ArrayList<>();
                l.add(rs.getString(1));
                l.add(rs.getString(2));
                l.add(rs.getString(3));
                l.add(rs.getString(4));
                list.add(l);
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
            list.clear();
            return list;
        }
        return list;
    }
    public static boolean changeProductPriceofProducttable(ProductDTO obj)
    {
        Database_connection connection  = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String query = "UPDATE PRODUCT_TABLE SET price_product = ? WHERE serial_number = ?";
            PreparedStatement ps = connection1.prepareStatement(query);
            ps.setLong(1,obj.getPrice());
            ps.setString(2,obj.getSerialno());
            int status  = ps.executeUpdate();
            if(status>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
            return false;
        }
    }
}