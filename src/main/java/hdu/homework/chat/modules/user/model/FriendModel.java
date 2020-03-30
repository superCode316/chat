//package hdu.homework.chat.modules.user.model;
//
//import hdu.homework.chat.entity.bean.database.Friend;
//import hdu.homework.chat.entity.bean.response.FriendInfo;
//import hdu.homework.chat.entity.bean.response.FriendName;
//import hdu.homework.chat.entity.mapper.FriendInfoMapper;
//import hdu.homework.chat.entity.mapper.FriendMapper;
//import hdu.homework.chat.entity.mapper.FriendNameMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.sql.SQLException;
//import java.util.List;
//
///**
// * created by 钱曹宇@supercode on 3/8/2020
// */@Repository
//public class FriendModel {
//    private final String add = "insert into friends(user_id, friend_id) value (?,?)";
//    private final String getFriend = "select f_id, f_name from v_user_friends where u_id = ?";
//    private final String getFriends = "select u_id, account, name, profile_url, birth, province, city, email, sign from user where u_id in (select f_id from v_user_friends where user.u_id = ?)";
//    private final String getApply = "select * from v_state where user_id = ? or friend_id = ?";
//    private final String updateApply = "update friends set is_agree = ? where user_id = ? and friend_id = ? order by add_time desc limit 1";
//    private JdbcTemplate template;
//
//    public FriendModel(JdbcTemplate template) {
//        this.template = template;
//    }
//
//    public void addFriend(Integer fid, Integer uid) {
//        template.update(add, uid, fid);
//    }
//
//    public List<FriendInfo> getFriends(Integer uid) {
//        return template.query(getFriends, new FriendInfoMapper(), uid);
//    }
//
//    public void updateApply(Integer agree, Integer uid, Integer fid) {
//        template.update(updateApply, agree, uid, fid);
//    }
//
//    public List<Friend> getApplies(Integer uid) {
//        return template.query(getApply, new FriendMapper("userId","friendId","isAgree"), uid, uid);
//    }
//}
