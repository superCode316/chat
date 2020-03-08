package hdu.homework.chat.modules.user.model;

import hdu.homework.chat.entity.bean.User;
import hdu.homework.chat.entity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Repository
public class UserModel {
    private JdbcTemplate template;
    private final String selectWherePhoneQuery = "select * from user where account = ?";
    private final String countQuery = "select count(*) from user where account = ?";
    private final String insertQuery = "insert into user(account, pwd, creaTime) value (?,?,?)";
    private final String getUidQuery = "select u_id from user where account = ?";

    @Autowired
    public UserModel(JdbcTemplate template) {
        this.template = template;
    }

    public User getUserByPhone(String phone) {
        return template.queryForObject(selectWherePhoneQuery, new UserMapper(), phone);
    }

    public Integer getUidByPhone(String phone) {
        return template.queryForObject(getUidQuery, Integer.class, phone);
    }

    public Long getUserCount(String username) {
        return template.queryForObject(countQuery, Long.class, username);
    }

    public void addUser(String username, String password, String upTime) {
        template.update(insertQuery, username, password, upTime);
    }


}
