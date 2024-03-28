package View;
import java.util.*;
import Resource.ProductDTO;
import Resource.User;
import Util.Input;
import java.util.ArrayList;
public class UserView
{
    public static int gettinguserinputfrmUser()
    {
        int n;
        while (true)
        {
            System.out.println(" ".repeat(45)+"Press 1 : View Products");
            System.out.println(" ".repeat(45)+"Press 2 : Purchase Products");
            System.out.println(" ".repeat(45)+"Press 3 : Modify my Details");
            System.out.println(" ".repeat(45)+"Press 4 : Purchase History");
            System.out.println(" ".repeat(45)+"Press 5 : Add Money to Wallet");
            System.out.println(" ".repeat(45)+"Press 6 : View Balance Amount  in Wallet");
            System.out.println(" ".repeat(45)+"Press 7 : Log out");
            System.out.print("Enter your Choice : ");
            try
            {
                n = Input.getInt();
                if(n<=7)
                    return n;
            }
            catch (Exception e)
            {
                UpdateView.InputMismatchError();
            }
        }
    }
    public static List<List<ProductDTO>> UserOrderItemsbyserialnumber()
    {
        List<List<ProductDTO>> list = new ArrayList<>();
        while (true)
        {
            List<ProductDTO> l = new ArrayList<>();
            System.out.println("------------Enter Serial Number to [Add to Cart]--------");
            System.out.println("Press 0 : To Exit of this Page else continue on this page");
            System.out.print("Serial Number-Add Cart / Press 0 - Exit : ");
            String serial = Input.getString();
            if(serial.equalsIgnoreCase("0"))
            {
                return list;
            }
            l.add(new ProductDTO(serial));
            list.add(l);
        }
    }
    public static String getHomeAddressofuser()
    {
        Input.getStringLine();
        System.out.print("Enter Your Current Home Address : ");
        return Input.getStringLine();
    }
    public static String getPinofuser()
    {
        System.out.print("Enter Pin Address : ");
        return Input.getStringLine();
    }
    public static long getMoneyofusertowallet()
    {
        while(true)
        {
            System.out.println("Enter Your Amount : ");
            try
            {
                return Input.getLong();
            }
            catch (Exception e)
            {
               UpdateView.InputMismatchError();
               Input.getStringLine();
            }
        }

    }
    public static int clarifymodify()
    {
        System.out.println(" ".repeat(45)+"Press 1 : Modify Password");
        System.out.println(" ".repeat(45)+"Press 2 : Modify Phone Number");
        System.out.println(" ".repeat(45)+"Press 3 : Modify Home Address");
        System.out.println(" ".repeat(45)+"Press 4 : Modify Pin Number");
        System.out.println(" ".repeat(45)+"Press 5 : Exit of this Page");
        System.out.print("Enter your Choice : ");
        int n;
        while(true)
        {
            try
            {
                n = Input.getInt();
                return n;
            }
            catch (Exception e)
            {
               UpdateView.InputMismatchError();
               Input.getStringLine();
            }
        }
    }
    public static String getnewPassword()
    {
        Input.getStringLine();
        System.out.println("Enter new Password : ");
        return Input.getStringLine();
    }
    public static String getnewnumber()
    {
        Input.getStringLine();
        System.out.println("Enter new Mobile Number : ");
        return Input.getStringLine();
    }
    public static String getnewaddress()
    {
        Input.getStringLine();
        System.out.println("Enter new Home Address : ");
        return Input.getStringLine();
    }
    public static void ShowBalanceofUser(long n, User obj)
    {
        System.out.println("User Name : "+obj.getEmail());
        System.out.println("Balance : "+n+".Rs");
    }
    public static void displayconformationtag()
    {
        System.out.println(" ".repeat(45)+"----------------------Payment Conformation---------------------");
        System.out.println(" ".repeat(45)+"Confirm your otp will automatically,debit money frm your Wallet");
        System.out.println(" ".repeat(45)+"---------------------------------------------------------------");
    }
    public static String getnewpinnumber()
    {
        System.out.println("Enter new Pin Number : ");
        return Input.getStringLine();
    }
}
