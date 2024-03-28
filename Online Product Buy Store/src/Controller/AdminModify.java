package Controller;
import Model.AdminDAO;
import Resource.ProductDTO;
import View.DisplayView;
import View.UpdateView;
import java.util.ArrayList;
import java.util.List;
public class AdminModify
{
    public static void modifydataofproducts(int n)
    {
        if(n==1)
        {
            List<ProductDTO> list = UpdateView.updateProductDetails();
            boolean flag = AdminDAO.addnewProductintable(list);//add new product
            DisplayView.Display_status_addpro(flag);
        }
        else if(n==2)
        {
            //view product details
            ArrayList<ArrayList<String>> list = AdminDAO.getAlltheproductdetails();
            DisplayView.displayProductDetails(list);
        }
        else if(n==3)
        {
            ArrayList<ArrayList<String>> list = AdminDAO.getAlltheproductdetails();
            DisplayView.displayProductDetails(list);
            ProductDTO obj = UpdateView.changeProductPrice();
           boolean status =  AdminDAO.changeProductPriceofProducttable(obj);
           DisplayView.Display_status_addpri(status);
        }
    }
}
