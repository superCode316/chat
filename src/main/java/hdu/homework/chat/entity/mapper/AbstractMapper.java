package hdu.homework.chat.entity.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractMapper<T> implements RowMapper<T>, CustomMapper<T> {

    private List<String> args;
    public AbstractMapper(String...args){this.args = Arrays.asList(args);}
    public AbstractMapper(){}
    @Override
    public T mapRow(ResultSet resultSet, int i) throws SQLException {
        Class<T> clazz = getClazz();
        Field[] fields = args!=null&&args.size()!=0 ? getRequiredFields(clazz.getDeclaredFields()) : clazz.getDeclaredFields();
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

    private Field[] getRequiredFields(Field[] fields) {
        Field[] result = new Field[args.size()];
        int i = 0;
        for (Field field : fields) {
            if (args.contains(field.getName()))
                result[i++] = field;
        }
        return result;
    }

    private String getMethodName(Field field) {
        String val = field.getName();
        return
                val.substring(0, 1).toUpperCase() + val.substring(1);
    }


}
