package hdu.homework.chat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getNowDateString() {
        return format.format(new Date());
    }
    public static String getDateString(Date date) {
        return format.format(date);
    }
}
