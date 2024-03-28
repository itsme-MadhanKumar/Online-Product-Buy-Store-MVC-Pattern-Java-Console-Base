package View;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DisplayView {
    public static void displayUserDetails(ArrayList<ArrayList<String>> list) {
        System.out.printf(
                "---------------------------------------------------------------------------------------------------%n");
        System.out.printf("|%-20s|%-30s|%-20s|%-10s|%-7s|%n",
                "User Name", "E-Mail id", "Passwords", "Phone", "Role");
        System.out.printf(
                "---------------------------------------------------------------------------------------------------%n");
        for (ArrayList<String> strings : list) {
            System.out.printf(("|%-20s|%-30s|%-20s|%-10s|%-7s|%n"),
                    strings.get(0), strings.get(1), strings.get(2), strings.get(3), strings.get(4));
        }
        System.out.printf(
                "---------------------------------------------------------------------------------------------------%n");
    }

    public static void displayProductDetails(ArrayList<ArrayList<String>> list) {
        System.out.printf(
                "---------------------------------------------------------------------------------------------------%n");
        System.out.printf("|%-15s|%-40s|%-20s|%-20s|%n",
                "Serial Number", "Product Names", "Price of Product", "Avalilable Quantity");
        System.out.printf(
                "---------------------------------------------------------------------------------------------------%n");
        for (ArrayList<String> strings : list) {
            System.out.printf(("|%-15s|%-40s|%-20s|%-20s|%n"),
                    strings.get(0), strings.get(1), strings.get(2), strings.get(3));
        }
        System.out.printf(
                "---------------------------------------------------------------------------------------------------%n");
    }
    //--------------------------------------------------------------display purchase pro details and price

    public static void displaypurchaseproductdetailsandprice(LinkedHashMap<String, Long> items, long sum) {
        System.out.println(" ".repeat(45) + "---------------------------------------------------------");
        System.out.printf(" ".repeat(45) + "| %-35s | %-20s |%n", "Product Details", "Product Price");
        System.out.println(" ".repeat(45) + "---------------------------------------------------------");

        for (Map.Entry<String, Long> entry : items.entrySet()) {
            System.out.printf(" ".repeat(45) + "| %-35s | %-20d |%n", entry.getKey(), entry.getValue());
        }

        System.out.println(" ".repeat(45) + "---------------------------------------------------------");
        System.out.printf(" ".repeat(45) + "| %-35s | %-20d |%n", "Total Price", sum);
        System.out.println(" ".repeat(45) + "---------------------------------------------------------");
    }

    public static void displayofalldetailsofsellinginfo(ArrayList<ArrayList<String>> list) {
        System.out.printf(
                "---------------------------------------------------------------------------------------------------%n");
        System.out.printf("|%-30s|%-20s|%-20s|%-20s|%n",
                "User_Email_ID", "Purchase Date", "Purchase Time", "Total Purchased");
        System.out.printf(
                "---------------------------------------------------------------------------------------------------%n");
        for (ArrayList<String> strings : list) {
            System.out.printf(("|%-30s|%-20s|%-20s|%-20s|%n"),
                    strings.get(0), strings.get(1), strings.get(2), strings.get(3));
        }
        System.out.printf(
                "---------------------------------------------------------------------------------------------------%n");

    }

    public static void DisplayDAOError(SQLException e) {
        System.out.println(e.getMessage());
    }

    public static void DisplayPaymentStatus(int n) {
        if (n >= 0) {
            System.out.println(" ".repeat(45) + "Payment Credited Successfully 💳✅");
        } else {
            System.out.println(" ".repeat(45) + "Payment Credited Failed ❌👎");
        }
    }

    public static void Display_product_id_notfound(String s) {
        System.out.println("Product with serial number " + s + " not found.");
    }

    public static void Display_sufficient_error() {
        System.out.println(" ".repeat(45) + "Not Having sufficient Amount 🫰💸");
    }

    public static void Display_status_ofchangepassword(boolean flag1) {
        if (flag1) {
            System.out.println("Password change is success");
        } else {
            System.out.println("Error on change of password");
        }
    }

    public static void Display_status_ofchangeNumber(boolean flag1) {
        if (flag1) {
            System.out.println("Phone Number change is success");
        } else {
            System.out.println("Error on change of Phone Number");
        }
    }

    public static void Display_status_ofchangeAddress(boolean flag1) {
        if (flag1) {
            System.out.println("Address change is success");
        } else {
            System.out.println("Error on change of Address");
        }
    }

    public static void Display_status_ofchangePin(boolean flag1) {
        if (flag1) {
            System.out.println("Pin Address change is success");
        } else {
            System.out.println("Error on change of Pin Address");
        }
    }

    public static void Display_Redirect_page() {
        System.out.println("Redirecting to Payment Page in:");
        try {
            for (int i = 5; i >= 0; i--) {
                System.out.print("\r" + i);
                Thread.sleep(1000);
            }
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void Display_Credit_status(boolean flag)
    {
        if(flag)
        {
            System.out.println(" ".repeat(45) + "Payment Done 💳 - Let our Delivery Will Reach You Soon 🚚");
        }
    }
    public static void Display_failed_status()
    {
        System.out.println(" ".repeat(45) +"PAYMENT - CANCELLED 👎❌");
    }
    public static void Display_Log_out()
    {
        System.out.println(" ".repeat(45)+"Logged - Out ✅");
    }
    public static void Display_Log_in()
    {
        System.out.println(" ".repeat(45)+"Logged - in ✅");
    }
    public static void Display_Access_Status()
    {
        System.out.println("Invalid Email and Password - ACCESS DENIED ❌");
    }
    public static void Display_User_Exists()
    {
        System.out.println(" ".repeat(45)+"---User Already Exists---");
    }
    public static void Display_Reg_complete()
    {
        System.out.println("".repeat(45)+"---Your new Registeration is compleated---");
        System.out.println("".repeat(45)+"-----Now click login button and login-----");
    }
    public static void Display_wentwrongstatus()
    {
        System.out.println("Something went wrong");
    }
    public static void Display_Thankyou()
    {
        System.out.println("Thank You For Shopping");
    }
    public static void Display_Kindlywait()
    {
        System.out.println("This Process May take up to 10 Seconds Kindly wait");
    }
    public static void Display_status_addpro(boolean flag)
    {
        if(flag)
        {
            System.out.println("success");
        }
        else
        {
            System.out.println("something went wrong");
        }
    }
    public static void Display_status_addpri(boolean status)
    {
        if(status)
        {
            System.out.println("Update Price Successful");
        }
        else
        {
            System.out.println("Update faild");
        }
    }
}
