package Controller;
import Model.PurchaseDAO;
import Model.UserDAO;
import Util.Input;
import View.*;
import java.util.ArrayList;
public class Admincontroller
{
    public static void startofAdmincontroller()
    {
        boolean flag = false;
        while(!flag)
        {
            try
            {
                int num = AdminView.ViewofAdmin();
                if (num == 1)
                {
                    ArrayList<ArrayList<String>> obj = UserDAO.getUserDetails();
                    if (obj.isEmpty())
                    {
                        DisplayView.Display_wentwrongstatus();
                    }
                    else
                    {
                        DisplayView.displayUserDetails(obj);
                    }
                }
                else if (num == 2)
                {
                    int n = AdminView.returnchoiceofmodifyaddproducts();
                    AdminModify.modifydataofproducts(n);
                }
                else if (num == 3)
                {
                    ArrayList<ArrayList<String>> list = PurchaseDAO.retriveDatafromsellingtable();
                    DisplayView.displayofalldetailsofsellinginfo(list);
                }
                else if (num == 4)
                {
                    DisplayView.Display_Log_out();
                    flag = true;
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
