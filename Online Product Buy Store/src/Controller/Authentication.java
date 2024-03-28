package Controller;
import Model.UserDAO;
import Resource.Admin;
import Resource.EmailSender;
import Resource.User;
import View.DisplayView;
import View.Login;
import View.UserView;
import java.sql.SQLException;
import java.util.LinkedHashMap;
public class Authentication
{
    // --------------------------------------------------Email verification for user(Sender)
    public boolean checkEmailbyotp(String email, User user)
    {
        DisplayView.Display_Kindlywait();
        EmailSender obj = new EmailSender();
        boolean flag = obj.sendEmail(email,user);
        if(!flag)
        {
            return false;
        }
        String getOtp = Login.getOtp();
        String sentotp = EmailSender.retriveSentOTP();
        return getOtp.equals(sentotp);
    }
    // --------------------------------------------------Email verification for Admin(Sender)
    public boolean checkEmailbyotpbyAdmin(String email,Admin admin)
    {
        DisplayView.Display_Kindlywait();
        EmailSender obj = new EmailSender();
        boolean flag = obj.sendEmailAdmin(email,admin);
        if(!flag)
        {
            return false;
        }
        String getOtp = Login.getOtp();
        String sentotp = EmailSender.retriveSentOTP();
        return getOtp.equals(sentotp);
    }
    //-------------------------------------------------Login Access For User(Sender)
    public boolean gotoUserAuthentication(String email,String password) throws SQLException
    {
        UserDAO obj = new UserDAO();
        return obj.isUserAccesstheApplication(email,password);
    }
    //-------------------------------------------------payment for user(Sender)
    public static boolean paymentsendertoDAO(String email, User user, LinkedHashMap<String,Long> items,long sum)
    {
       DisplayView.Display_Kindlywait();
        EmailSender obj = new EmailSender();
        boolean flag = obj.paymentproceesEmail(email,user,items,sum);
        if(!flag)
        {
            return false;
        }
        UserView.displayconformationtag();
        String getOtp = Login.getOtp();
        String sentotp = EmailSender.retriveSentOTP();
        return getOtp.equals(sentotp);
    }
    public static boolean verifyforcreditmoney(String email, User user)
    {
       DisplayView.Display_Kindlywait();
        EmailSender obj = new EmailSender();
        boolean flag = obj.sendEmail(email,user);
        if(!flag)
        {
            return false;
        }
        String getOtp = Login.getOtp();
        String sentotp = EmailSender.retriveSentOTP();
        return getOtp.equals(sentotp);
    }
}
