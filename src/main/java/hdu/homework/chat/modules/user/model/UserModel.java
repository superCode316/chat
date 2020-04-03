package hdu.homework.chat.modules.user.model;

import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.entity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Repository
public class UserModel {
    private JdbcTemplate template;
    private final String countQuery = "select count(*) from user where account = ?";
    private final String insertQuery = "insert into user(account, password) values (?,?)";
    private final String getUidQuery = "select u_id from user where account = ?";
    private final String limitUserInfo = "select u_id, account, profile_url from user where u_id = ?";
    private final String limitUserInfoByAccount = "select u_id, account, profile_url from user where account = ?";
    private final String userInfoByAccount = "select * from user where account = ?";
    private final String authUserInfo = "select u_id, account, password, create_time, profile_url from user where account = ?";

    @Autowired
    public UserModel(JdbcTemplate template) {
        this.template = template;
    }

    public User getAuthenticationInfo(String phone) {
        return template.queryForObject(authUserInfo, new UserMapper("uid","account","password","createTime","profileURL"), phone);
    }

    public Integer getUidByAccount(String phone) {
        return template.queryForObject(getUidQuery, Integer.class, phone);
    }

    public Long getUserCount(String account) {
        return template.queryForObject(countQuery, Long.class, account);
    }

    public void addUser(String account, String password) {
        template.update(insertQuery, account, password);
    }

    public User getLimitUserInfo(Integer uid) {
        List<User> userList = template.query(limitUserInfo, new UserMapper("uid", "account", "profileURL"), uid);
        return DataAccessUtils.uniqueResult(userList);
    }

    public User getLimitUserInfo(String account) {
        List<User> userList = template.query(limitUserInfoByAccount, new UserMapper("uid", "account", "profileURL"), account);
        return DataAccessUtils.uniqueResult(userList);
    }

    public User getFullUserInfo(String account) {
        List<User> userList = template.query(userInfoByAccount, new UserMapper(), account);
        return DataAccessUtils.uniqueResult(userList);
    }
//
//    public void updateProfile(User user) {
//        template.update()
//    }
}
