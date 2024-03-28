package Controller;
import Model.AdminDAO;
import Model.PurchaseDAO;
import Model.UserDAO;
import Resource.*;
import Resource.ProductDTO;
import View.DisplayView;
import View.UserView;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static Model.UserDAO.changepinnumber;
import java.time.format.DateTimeFormatter;
public class UserModify
{
    public static void userAddtothecart()
    {
        ArrayList<ArrayList<String>> list = AdminDAO.getAlltheproductdetails();
        DisplayView.displayProductDetails(list);
    }
    public static boolean processthepaymentinemail(List<List<ProductDTO>> list,User obj) throws SQLException
    {
        String homeaddress = UserView.getHomeAddressofuser();
        String pin = UserView.getPinofuser();
        LinkedHashMap<String,Long> items = PurchaseDAO.checklisttheItems(list);
        long sum = PurchaseDAO.calculate_price_of_products(list);
        if(!items.isEmpty())
        {
            DisplayView.displaypurchaseproductdetailsandprice(items, sum);
            boolean flag = Authentication.paymentsendertoDAO(obj.getEmail(),obj,items,sum);
            if(flag)
            {
                boolean money = PurchaseDAO.ishavingmoney_then_debit(obj,sum);
                if(money)
                {
                    //add address to user
                    UserDAO.changeexistingaddress(obj,homeaddress);
                    changepinnumber(obj,pin);
                    return true;
                }
                else
                {
                    DisplayView.Display_sufficient_error();
                    return false;
                }
            }
        }
        // send otp and store address,mofify price and count of pro if true
        return false;
    }
    //--------------------------------------------------------------------------user modify details
    public static void usermodifydetails(User obj,int n)
    {
       if(n==1)
       {
           String passwords = UserView.getnewPassword();
           boolean flag = UserDAO.changeexistingpassword(obj,passwords);
           DisplayView.Display_status_ofchangepassword(flag);
       }
       else if(n==2)
       {
           String phone = UserView.getnewnumber();
           boolean flag1 = UserDAO.changeexistingnumber(obj,phone);
           DisplayView.Display_status_ofchangeNumber(flag1);
       }
       else if(n==3)
       {
           String phone = UserView.getnewaddress();
           boolean flag = UserDAO.changeexistingaddress(obj,phone);
           DisplayView.Display_status_ofchangeAddress(flag);
       }
       else if(n==4)
       {
           String phone = UserView.getnewpinnumber();
           boolean fla1 = changepinnumber(obj,phone);
           DisplayView.Display_status_ofchangePin(fla1);
       }
    }
    public static String getDate()
    {
        LocalDate d = DateTimeDTO.getCurrentDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateString = d.format(formatter);
        return currentDateString;
    }
    public static String getTime()
    {
        LocalTime t = DateTimeDTO.getCurrentTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh-mm-ss");
        String currentTimeString = t.format(formatter);
        return currentTimeString;
    }
}
