package Controller;
import Model.AdminDAO;
import Model.PurchaseDAO;
import Model.UserDAO;
import Resource.ProductDTO;
import Resource.User;
import Util.Input;
import View.DisplayView;
import View.UpdateView;
import View.UserView;
import java.util.*;
public class Usercontroller
{
    public static void startofUserApplication(User obj)
    {

        while (true)
        {
            try
            {
                int num = UserView.gettinguserinputfrmUser();
                if (num == 1)
                {
                    ArrayList<ArrayList<String>> list = AdminDAO.getAlltheproductdetails();
                    DisplayView.displayProductDetails(list);
                }
                else if (num == 2)
                {
                    UserModify.userAddtothecart();
                    List<List<ProductDTO>> list = UserView.UserOrderItemsbyserialnumber();
                    if(!list.isEmpty())
                    {
                        DisplayView.Display_Redirect_page();
                        long sum = PurchaseDAO.calculate_price_of_products(list);
                        boolean flag  = UserModify.processthepaymentinemail(list,obj);
                        boolean f1 = PurchaseDAO.decreasebyproducts(list);
                        if(flag && f1)
                        {
                            String date = UserModify.getDate();
                            String time = UserModify.getTime();
                            boolean f2 = PurchaseDAO.storesellinginformationintable(obj,sum,date,time);
                            // store user,date,time,sum
                            DisplayView.Display_Credit_status(f2);
                        }
                        else
                        {
                            DisplayView.Display_failed_status();
                        }
                    }
                }
                else if (num == 3)
                {
                    int n = UserView.clarifymodify();
                    UserModify.usermodifydetails(obj,n);
                }
                else if(num==4)
                {
                    ArrayList<ArrayList<String>> list = PurchaseDAO.retriveDatafromsellingtableUser(obj);
                    DisplayView.displayofalldetailsofsellinginfo(list);
                }
                else if(num==5)
                {
                    long n = UserView.getMoneyofusertowallet();
                    boolean flag = Authentication.verifyforcreditmoney(obj.getEmail(),obj);
                    if(flag)
                    {
                        UserDAO obj1 = new UserDAO();
                        obj1.addMoneytothewallet(n,obj);
                    }
                    else
                    {
                       DisplayView.Display_failed_status();
                    }
                }
                else if(num==6)
                {
                    long b = UserDAO.getBalanceofUser(obj);
                    UserView.ShowBalanceofUser(b,obj);
                }
                else if(num==7)
                {
                   DisplayView.Display_Log_out();
                    return ;
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
