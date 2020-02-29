package hdu.homework.chat.modules.user.model;

import hdu.homework.chat.entity.bean.User;
import hdu.homework.chat.entity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserModel {
    private JdbcTemplate template;
    private final String selectWherePhoneQuery = "select * from user where u_phone = ?";
    private final String countQuery = "select count(*) from user where u_phone = ?";
    private final String insertQuery = "insert into user(u_phone, u_pwd, creaTime) value (?,?,?)";

    @Autowired
    public UserModel(JdbcTemplate template) {
        this.template = template;
    }

    public User getUserByPhone(String phone) {
        return template.queryForObject(selectWherePhoneQuery, new UserMapper(), phone);
    }

    public Long getUserCount(String username) {
        return template.queryForObject(countQuery, Long.class, username);
    }

    public void addUser(String username, String password, String upTime) {
        template.update(insertQuery, username, password, upTime);
    }


}
