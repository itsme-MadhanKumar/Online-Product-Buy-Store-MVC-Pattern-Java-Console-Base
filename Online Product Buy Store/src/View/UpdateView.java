package View;
import java.util.*;
import Resource.ProductDTO;
import Util.Input;
public class UpdateView
{
    public static List<ProductDTO> updateProductDetails()
    {
        while(true)
        {
            try
            {
                Input.getStringLine();
                System.out.print("Enter Product Serial Number : ");
                String serial = Input.getString();
                Input.getStringLine();
                System.out.print("Enter Product Nmae : ");
                String product_name = Input.getStringLine();
                try
                {
                    System.out.print("Enter Price : ");
                    long price = Input.getLong();
                    System.out.print("Enter Quantity of the Product : ");
                    long quantity = Input.getLong();
                    ArrayList<ProductDTO> list = new ArrayList<>();
                    list.add(new ProductDTO(serial, product_name, price, quantity));
                    return list;
                }
                catch (Exception e)
                {
                  UpdateView.InputMismatchError();
                  Input.getStringLine();
                }
            }
            catch (Exception e)
            {
                UpdateView.InputMismatchError();
            }
        }

    }
    public static ProductDTO changeProductPrice()
    {
        while (true)
        {
            System.out.println("Enter Product Serial Number  : ");
            String s = Input.getString();
            System.out.println("Enter Modifying Price : ");
            try
            {
                long price = Input.getLong();
                ProductDTO obj = new ProductDTO();
                obj.setPrice(price);
                obj.setSerialno(s);
                return obj;
            }
            catch (Exception e)
            {
                UpdateView.InputMismatchError();
                Input.getStringLine();
            }
        }
    }
    public static void InputMismatchError()
    {
        System.out.println("Input Format Invalid");
    }
}
