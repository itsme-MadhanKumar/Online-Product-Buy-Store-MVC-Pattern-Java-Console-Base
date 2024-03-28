package View;
import Util.Input;

public class AdminView
{
    public static int ViewofAdmin()
    {
        int n;
        while(true)
        {
            System.out.println(" ".repeat(45)+"Press 1 : View Customer Details");
            System.out.println(" ".repeat(45)+"Press 2 : Modify/Add Product Details and Price");
            System.out.println(" ".repeat(45)+"Press 3 : View Statistics of Products");
            System.out.println(" ".repeat(45)+"Press 4 : Log out");
            System.out.println("Enter your choice : ");
            try
            {
                n = Input.getInt();
                return n;
            }
            catch (Exception e)
            {
                UpdateView.InputMismatchError();
            }
        }
    }
    public static int returnchoiceofmodifyaddproducts()
    {
        while(true)
        {
            System.out.println(" ".repeat(45)+"Press 1 : Add a New Product");
            System.out.println(" ".repeat(45)+"Press 2 : View Product Details");
            System.out.println(" ".repeat(45)+"Press 3 : Modify Product Price");
            System.out.println(" ".repeat(45)+"Press 4 : Return to Back Page");
            System.out.print("Enter your Choice : ");
            int n ;
            try
            {
                n = Input.getInt();
                if(n<=4)
                {
                    return n;
                }
            }
            catch (Exception e)
            {
               UpdateView.InputMismatchError();
            }
        }
    }
}
