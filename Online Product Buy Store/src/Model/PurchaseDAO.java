package Model;
import Resource.ProductDTO;
import Resource.User;
import Util.Database_connection;
import View.DisplayView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class PurchaseDAO
{
    public static LinkedHashMap<String, Long> checklisttheItems(List<List<ProductDTO>> list) {
        LinkedHashMap<String, Long> map = new LinkedHashMap<>();
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String query = "SELECT product_name, price_product FROM PRODUCT_TABLE WHERE serial_number = ?";
            for (List<ProductDTO> serial : list) {
                for (ProductDTO product : serial) {
                    PreparedStatement ps = connection1.prepareStatement(query);

                    ps.setString(1, product.getOrdered());

                    ResultSet rs = ps.executeQuery();
                    {
                        if (rs.next())
                        {
                            String pro_name = rs.getString("product_name");
                            long pro_price = rs.getLong("price_product");
                            map.put(pro_name, pro_price);
                        }
                    }
                }
            }

        } catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return map;
    }
    public static Long calculate_price_of_products(List<List<ProductDTO>> list)
    {
        long sum = 0;
        Database_connection connection = new Database_connection();
        try
        {
            Connection conn = connection.connect();
            String query = "SELECT price_product FROM PRODUCT_TABLE WHERE serial_number = ?";

            for (List<ProductDTO> serial : list) {
                for (ProductDTO product : serial) {
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, product.getOrdered());

                    try (ResultSet rs = ps.executeQuery())
                    {
                        if (rs.next()) {
                            long price = rs.getLong("price_product");
                            sum += price;
                        }
                        else
                        {
                           DisplayView.Display_product_id_notfound(product.getOrdered());
                        }
                    }
                }
            }
        }
       catch (SQLException e)
       {
           DisplayView.DisplayDAOError(e);
       }
        return sum;
    }
    public static boolean ishavingmoney_then_debit(User obj,long sum)
    {
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String query = "SELECT Money FROM USER_TABLE WHERE email_id = ?";
            PreparedStatement ps  = connection1.prepareStatement(query);
            ps.setString(1,obj.getEmail());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                long currentbalance = rs.getLong("Money");
                if(currentbalance>=sum)
                {
                    String query1 = "UPDATE USER_TABLE SET Money = Money - ? WHERE email_id = ?";
                    PreparedStatement ps1 = connection1.prepareStatement(query1);
                    ps1.setLong(1,sum);
                    ps1.setString(2,obj.getEmail());
                    ps1.executeUpdate();
                    return true;
                }
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return false;
    }
    public static boolean decreasebyproducts(List<List<ProductDTO>> list)
    {
        Database_connection connection = new Database_connection();
        try
        {

            Connection connection1 = connection.connect();
            long m = 1;
            for(List<ProductDTO> pro : list)
            {
                for(ProductDTO serials : pro)
                {
                    String s_no = serials.getOrdered();
                    String query = "UPDATE PRODUCT_TABLE SET available_quantity = available_quantity - ? WHERE serial_number = ?";
                    PreparedStatement ps = connection1.prepareStatement(query);
                    ps.setLong(1,m);
                    ps.setString(2,s_no);
                    ps.executeUpdate();
                }
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
            return false;
        }
        return true;
    }
    //---------------------------------------------------------------------store in selling_Info
    public static boolean storesellinginformationintable(User obj, long sum, String date, String time)
    {
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String q = "INSERT INTO SELLING_INFO(user_name,date_purchase,time_purchase,total_purchase) VALUES(?,?,?,?)";
            PreparedStatement ps = connection1.prepareStatement(q);
            ps.setString(1,obj.getEmail());
            ps.setString(2,date);
            ps.setString(3,time);
            ps.setLong(4,sum);
            int status = ps.executeUpdate();
            if(status!=1)
            {
                return false;
            }
            return true;
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return false;
    }
    //-------------------------------------------------------Display of Selling table
    public static ArrayList<ArrayList<String>> retriveDatafromsellingtable() // Admin
    {
        ArrayList<ArrayList<String>> list =  new ArrayList<>();
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String q = "SELECT user_name,date_purchase,time_purchase,total_purchase FROM SELLING_INFO";
            PreparedStatement ps = connection1.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                ArrayList<String> l = new ArrayList<>();
                String name = rs.getString("user_name");
                String date = rs.getString("date_purchase");
                String time = rs.getString("time_purchase");
                String s = Long.toString(rs.getLong("total_purchase"));
                l.add(name);
                l.add(date);
                l.add(time);
                l.add(s);
                list.add(l);
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return list;
    }
    public static ArrayList<ArrayList<String>> retriveDatafromsellingtableUser(User obj) // user
    {
        ArrayList<ArrayList<String>> list =  new ArrayList<>();
        Database_connection connection = new Database_connection();
        try
        {
            Connection connection1 = connection.connect();
            String q = "SELECT user_name,date_purchase,time_purchase,total_purchase FROM SELLING_INFO WHERE user_name = ?";
            PreparedStatement ps = connection1.prepareStatement(q);
            ps.setString(1,obj.getEmail());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                ArrayList<String> l = new ArrayList<>();
                String name = rs.getString("user_name");
                String date = rs.getString("date_purchase");
                String time = rs.getString("time_purchase");
                String s = Long.toString(rs.getLong("total_purchase"));
                l.add(name);
                l.add(date);
                l.add(time);
                l.add(s);
                list.add(l);
            }
        }
        catch (SQLException e)
        {
            DisplayView.DisplayDAOError(e);
        }
        return list;
    }
}
