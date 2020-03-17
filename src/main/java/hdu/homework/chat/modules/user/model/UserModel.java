package hdu.homework.chat.modules.user.model;

import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.entity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Repository
public class UserModel {
    private JdbcTemplate template;
    private final String selectWherePhoneQuery = "select u_id, account, pwd, create_time, profile_url from user where account = ?";
    private final String selectWhereUidQuery = "select u_id, account, pwd, create_time, profile_url from user where u_id = ?";
    private final String countQuery = "select count(*) from user where account = ?";
    private final String insertQuery = "insert into user(account, pwd) value (?,?)";
    private final String getUidQuery = "select u_id from user where account = ?";

    @Autowired
    public UserModel(JdbcTemplate template) {
        this.template = template;
    }

    public User getUserByPhone(String phone) {
        List<User> users =  template.query(selectWherePhoneQuery, new UserMapper("uid","account","password","createTime","profileURL"), phone);
        if (users.size() == 0)
            return null;
        return users.get(0);
    }

    public User getUserByUid(Integer uid) {
        List<User> users =  template.query(selectWhereUidQuery, new UserMapper("uid","account","password","createTime","profileURL"), uid);
        if (users.size() == 0)
            return null;
        return users.get(0);
    }

    public Integer getUidByAccount(String phone) {
        return template.queryForObject(getUidQuery, Integer.class, phone);
    }

    public Long getUserCount(String username) {
        return template.queryForObject(countQuery, Long.class, username);
    }

    public void addUser(String username, String password) {
        template.update(insertQuery, username, password);
    }

}
