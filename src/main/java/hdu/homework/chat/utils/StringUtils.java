package hdu.homework.chat.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    private static char random(char c) {
        return baseString.charAt((int)(c * Math.random()*10)%62);
    }
    private static char random() {
        return baseString.charAt((int)(Math.random()*10)%62);
    }
    public static String randomString(String...s) {
        StringBuilder sb = new StringBuilder();
        for (String str : s) {
            for (char c : str.toCharArray()) {
                sb.append(random(c));
            }
        }
        for (int i = sb.length(); i <=minLength; i++) {
            sb.append(random());
        }
        return sb.toString();
    }
    private static MessageDigest md = null;
    private static Logger log = LoggerFactory.getLogger("StringUtils");
    public static String encodeFile(String filename) {
        if (md == null) {
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                log.error("no algorithm md5");
                try {
                    md = MessageDigest.getInstance("md2");
                } catch (NoSuchAlgorithmException ee) {
                    log.error("no algorithm md2");
                    ee.printStackTrace();
                }
            }
        }
        if (md != null) {
            return new String(md.digest(filename.getBytes()));
        } else {
            return defaultAlgorithm(filename);
        }
    }
    private static String defaultAlgorithm(String filename) {
        StringBuilder sb = new StringBuilder();
        for (char b : filename.toCharArray()) {
            sb.append(b);
            sb.append(random());
        }
        return sb.toString();
    }
}
