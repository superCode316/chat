package hdu.homework.chat.entity.mapper;

import hdu.homework.chat.entity.bean.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends AbstractMapper<User> {
    @Override
    public Class<User> getClazz() {
        return User.class;
    }
}
