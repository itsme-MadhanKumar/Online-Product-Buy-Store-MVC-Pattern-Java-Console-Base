package View;
import Util.Input;
public class Welcome
{
    public static int start()
    {
        while(true)
        {
            System.out.println("-".repeat(90));
            System.out.println(" ".repeat(45) + "Press 1 : Admin Login");
            System.out.println(" ".repeat(45) + "Press 2 : User Login");
            System.out.println(" ".repeat(45) + "Press 3 : New Signup");
            System.out.println(" ".repeat(45) + "Press 0 : Exit ");
            System.out.print("Enter your Choise : ");
            int n;
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
}

