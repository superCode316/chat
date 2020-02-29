package hdu.homework.chat.entity.mapper;

import hdu.homework.chat.entity.bean.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUid(resultSet.getInt(1));
        user.setPhone(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setCreateTime(resultSet.getString(4));
        return user;
    }
}
