package hdu.homework.chat.entity.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractMapper<T> implements RowMapper<T>, CustomMapper<T> {
    @Override
    public T mapRow(ResultSet resultSet, int i) throws SQLException {
        Class<T> clazz = getClazz();
        Field[] fields = clazz.getDeclaredFields();
        Constructor<T> constructor;
        Object obj;
        try {
            constructor = clazz.getDeclaredConstructor();
            obj = constructor.newInstance();
            for (int iv = 0; iv < fields.length; iv++) {
                Method method = clazz.getMethod("set" + getMethodName(fields[iv]), fields[iv].getType());
                method.invoke(obj, resultSet.getObject(iv + 1, fields[iv].getType()));
            }
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new SQLException(e);
        }
        return (T) obj;
    }

    private String getMethodName(Field field) {
        String val = field.getName();
        return
                val.substring(0, 1).toUpperCase() + val.substring(1);
    }
}
