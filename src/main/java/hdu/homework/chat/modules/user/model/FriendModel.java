package hdu.homework.chat.modules.user.model;

import hdu.homework.chat.entity.bean.response.Friend;
import hdu.homework.chat.entity.mapper.FriendMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendModel {
    private final String add = "insert into friends(userID, friendID, addTime) value (?,?,?)";
    private final String getFriend = "(select friendID as fid, account as name from friends left join `user` on `user`.u_id = userID where userID = ?) union\n" +
            "(select userID as fid, account as name from friends left join `user` on `user`.u_id = userID where friendID = ?)";
    private JdbcTemplate template;

    public FriendModel(JdbcTemplate template) {
        this.template = template;
    }

    public void addFriend(Integer fid, Integer uid, String addDate) {
        template.update(add, uid, fid, addDate);
    }

    public List<Friend> getFriends(Integer uid) {
        return template.query(getFriend, new FriendMapper(), uid, uid);
    }
}
