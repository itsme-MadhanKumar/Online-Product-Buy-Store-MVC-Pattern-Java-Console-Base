package Util;
import java.util.Scanner;
public class Input
{
    public static Scanner input = new Scanner(System.in);
    public static String getString()
    {
        return input.next();
    }
    public static String getStringLine()
    {
        return input.nextLine();
    }
    public static int  getInt()
    {
        return input.nextInt();
    }
    public static long getLong()
    {
        return input.nextLong();
    }
}
