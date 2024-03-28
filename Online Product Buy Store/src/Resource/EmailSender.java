package Resource;
import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
public class EmailSender
{
    private  static String otp = "";
    public  boolean paymentproceesEmail(String email,User user,LinkedHashMap<String,Long> items,long sum)
    {
        StringBuilder str = new StringBuilder();
        str.append("=-=-=-=-=-=-=-WELCOME-=-=-=-=-=-=-=-=").append("\n");
        int c = 1;
        for(Map.Entry<String,Long> mapp : items.entrySet())
        {
            str.append(c++).append(" |Product Name : ").append(mapp.getKey()).append("------>").append(mapp.getValue()).append("Rs").append("\n");
        }
        str.append("Total Price : ").append(sum).append("Rs").append("\n").append("Please confirm your payment");
        String from = "madhanofficial08@gmail.com";
        String host = "smtp.gmail.com";
        String username = "madhanofficial08@gmail.com";
        String password = "nvdberblgyndoxeb";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
        });
        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Please Confirm your Order");
            otp = generate_otp();
            str.append("Your OTP is  : ").append(otp);
            message.setText(str.toString());
            Transport.send(message);
            System.out.println("OTP IS SENT TO YOUR MAIL ID -> "+user.getEmail());
            return true;
        }
        catch (MessagingException mex)
        {
            System.out.println(mex.getMessage()+", Mail id Not found");
            return false;
        }
    }
    public  boolean sendEmail(String email,User user)
    {
        String from = "madhanofficial08@gmail.com";
        String host = "smtp.gmail.com";
        String username = "madhanofficial08@gmail.com";
        String password = "nvdberblgyndoxeb";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
        });
        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Your One Time Password For Online Purchasing");
           otp = generate_otp();
            message.setText("YOUR OTP IS  : "+otp+". DONT SHARE WITH ANY ONE ,EVEN WE DONT ASK ANYMORE");
            Transport.send(message);
            System.out.println("OTP IS SENT TO YOUR MAIL ID -> "+user.getEmail());
            return true;
        }
        catch (MessagingException mex)
        {
            System.out.println(mex.getMessage()+", Mail id Not found");
            return false;
        }
    }
    //------------------------------------------------------------------for Admin
    public  boolean sendEmailAdmin(String email,Admin admin)
    {
        String from = "madhanofficial08@gmail.com";
        String host = "smtp.gmail.com";
        String username = "madhanofficial08@gmail.com";
        String password = "nvdberblgyndoxeb";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
        });
        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Your One Time Password For Online Purchasing");
            otp = generate_otp();
            message.setText("YOUR OTP IS  : "+otp+". DONT SHARE WITH ANY ONE ,EVEN WE DONT ASK ANYMORE");
            Transport.send(message);
            System.out.println("OTP IS SENT TO YOUR MAIL ID -> "+admin.getEmail());
            return true;
        }
        catch (MessagingException mex)
        {
            System.out.println(mex.getMessage()+", Mail id Not found");
            return false;
        }
    }
    public static String generate_otp()
    {
        Random random = new Random();
        int sixDigitNumber = 100000 + random.nextInt(900000);
        return Integer.toString(sixDigitNumber);
    }
    public static String retriveSentOTP()
    {
        return otp;
    }
}
