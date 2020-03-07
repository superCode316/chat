package hdu.homework.chat.modules.user.service;

import hdu.homework.chat.entity.bean.Group;
import hdu.homework.chat.entity.bean.User;
import hdu.homework.chat.entity.bean.response.Friend;
import hdu.homework.chat.modules.groups.model.GroupModel;
import hdu.homework.chat.modules.user.model.FriendModel;
import hdu.homework.chat.modules.user.model.UserModel;
import hdu.homework.chat.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private UserModel userModel;
    private GroupModel groupModel;
    private FriendModel friendModel;

    public UserService(UserModel userModel, GroupModel groupModel, FriendModel friendModel) {
        this.userModel = userModel;
        this.groupModel = groupModel;
        this.friendModel = friendModel;
    }

    public Map<String, Object> getFullUserInfo(String username) {
        Integer uid = userModel.getUidByPhone(username);
        User user = userModel.getUserByPhone(username);
        List<Group> groups = groupModel.getGroupsByUserName(username);
        return Map.of("userinfo", user, "groups", groups);
    }

    public boolean checkInGroup(String username, Integer gid) {
        List<Group> groups = groupModel.getGroupsByUserName(username);
        return groups.stream().anyMatch(group -> group.getG_id().equals(gid));
    }

    public boolean checkExist(String username) {
        return userModel.getUserByPhone(username) == null;
    }

    public void addFriend(String username, String friend) {
        friendModel.addFriend(userModel.getUidByPhone(friend), userModel.getUidByPhone(username), DateUtils.getNowDateString());
    }

    public List<Friend> getFriends(String username) {
        return friendModel.getFriends(userModel.getUidByPhone(username));
    }
}
