package hdu.homework.chat.utils;

/**
 * created by 钱曹宇@supercode on 3/17/2020
 */
public class StringUtils {
    public static boolean isDigit(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
