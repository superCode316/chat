package hdu.homework.chat.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
public class ConvertUtils {
    public static boolean convert(Object dest, Object src, Class<?> destClazz, Class<?> srcClazz ) {
        try {
            Field[] fields = srcClazz.getDeclaredFields();
            for (Field field : fields) {
                Method method = srcClazz.getMethod("get"+getName(field.getName()));
                Object value = method.invoke(src);
                Method method1 = destClazz.getMethod("set"+getName(field.getName()), field.getType());
                method1.invoke(dest, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static String getName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
