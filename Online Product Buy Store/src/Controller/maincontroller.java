package Controller;
import Model.AdminDAO;
import Model.UserDAO;
import Resource.Admin;
import Resource.User;
import Util.Input;
import View.*;
import java.sql.SQLException;
public class maincontroller
{
    public static void MainScreen() throws SQLException
    {
        while (true)
        {
            try
            {
                int num = Welcome.start();
                if (num == 1)
                {
                    Admin admin = Login.Adminlogin();
                    AdminDAO obj = new AdminDAO();
                    Authentication obj1 = new Authentication();
                    admin.setRole("Admin");
                    boolean flag = obj1.checkEmailbyotpbyAdmin(admin.getEmail(),admin);
                    String role = AdminDAO.getroles(admin.getEmail());
                    boolean access = obj.isAdminaccessedtheapplication(admin) && flag && (admin.getRole().equalsIgnoreCase(role));
                    if(access)
                    {
                        DisplayView.Display_Log_in();
                        Admincontroller.startofAdmincontroller();
                    }
                    else
                    {
                        DisplayView.Display_Access_Status();
                    }
                }
                //--------------------------------------------------------------------
                else if (num == 2)
                {
                    User obj = Login.userlogin();
                    Authentication obj1 = new Authentication();
                    boolean flag = obj1.gotoUserAuthentication(obj.getEmail(),obj.getPassword());
                    obj.setRole("User");
                    String role = UserDAO.getroles(obj.getEmail());
                    boolean flag2 = (obj.getRole().equalsIgnoreCase(role));
                    if(flag && flag2)
                    {
                        DisplayView.Display_Log_in();
                        Usercontroller.startofUserApplication(obj);
                    }
                    else
                    {
                        DisplayView.Display_Access_Status();
                    }
                }
                //--------------------------------------------------------------------
                else if (num == 3)
                {

                    User obj = Login.signupScreen();
                    UserDAO user = new UserDAO();
                    if (user.isUserAlreadyExists(obj))
                    {
                       DisplayView.Display_User_Exists();
                        MainScreen();
                    }
                    else
                    {
                        if (user.createnewUser(obj))
                        {
                          DisplayView.Display_Reg_complete();
                            MainScreen();
                        }
                        else
                        {
                           DisplayView.Display_wentwrongstatus();
                            MainScreen();
                        }
                    }
                }
                //-------------------------------------------------------------------
                else if (num == 0)
                {
                    DisplayView.Display_Thankyou();
                    System.exit(0);
                }
            }
            catch (Exception e)
            {
                UpdateView.InputMismatchError();
                Input.getStringLine();
            }
        }
    }
}
