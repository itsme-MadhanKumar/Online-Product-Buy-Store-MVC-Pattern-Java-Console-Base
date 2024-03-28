package Resource;
import java.time.LocalDate;
import java.time.LocalTime;
public class DateTimeDTO
{
    public static LocalTime getCurrentTime()
    {
        return LocalTime.now();
    }
    public static LocalDate getCurrentDate()
    {
        return LocalDate.now();
    }
}
