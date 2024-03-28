package View;
import Resource.Admin;
import Util.Input;
import Resource.User;

public class Login
{
    public static User userlogin()
    {
      System.out.print("Enter Your UserName : ");
      String email = Input.getString();
      System.out.print("Enter Your Password : ");
      String password = Input.getString();
        return new User(email,password);
    }
    public static Admin Adminlogin()
    {
        System.out.println("  Welcome to Sign Up page ");
        Input.getStringLine();
        System.out.print("Enter Admin Mail Id : ");
        String email = Input.getStringLine();
        System.out.print("Enter Admin Password : ");
        String password = Input.getStringLine();
        Admin obj = new Admin(email,password);
        obj.setRole("Admin");
        return obj;
    }
    public static User signupScreen()
    {
        System.out.println("  Welcome to Sign Up page ");
        Input.getStringLine();
        System.out.print("Enter Name: ");
        String name = Input.getStringLine();
        System.out.print("Enter Email: ");
        String email = Input.getStringLine();
        System.out.print("Enter Passward: ");
        String passward = Input.getStringLine();
        System.out.print("Enter Phone no: ");
        String phone = Input.getStringLine();
        User data = new User(name, email, phone, passward);
        data.setName(name);
        data.setEmail(email);
        data.setRole("User");
        return data;
    }
    public static String getOtp()
    {
        System.out.print("Enter Your OTP : ");
        return Input.getString();
    }

}
