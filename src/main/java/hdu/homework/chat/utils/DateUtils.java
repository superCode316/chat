package hdu.homework.chat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
public class DateUtils {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getNowDateString() {
        return format.format(new Date());
    }
    public static String getDateString(Date date) {
        return format.format(date);
    }
}
