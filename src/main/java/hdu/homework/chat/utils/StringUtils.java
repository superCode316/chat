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
    private static String baseString = "qpwoeirutylaksjdhfgmznxcvbQPWOEIRUTYALSKDJFHGMZNXBCV1234567890";
    private static int minLength = 20;
    public static String randomString(String...s) {
        StringBuilder sb = new StringBuilder();
        for (String str : s) {
            for (char c : str.toCharArray()) {
                sb.append(baseString.charAt((int)(c * Math.random()*10)%62));
            }
        }
        for (int i = sb.length(); i <=minLength; i++) {
            sb.append(baseString.charAt((int)(Math.random()*62)));
        }
        return sb.toString();
    }
}
