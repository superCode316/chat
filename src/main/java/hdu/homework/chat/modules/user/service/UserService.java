package hdu.homework.chat.modules.user.service;

import hdu.homework.chat.entity.bean.database.Group;
import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.modules.groups.model.GroupModel;
import hdu.homework.chat.modules.user.model.FriendModel;
import hdu.homework.chat.modules.user.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Service
public class UserService {
    private UserModel userModel;
    private FriendModel friendModel;
    private GroupModel groupModel;

    public UserService(UserModel userModel, GroupModel groupModel, FriendModel friendModel) {
        this.userModel = userModel;
        this.groupModel = groupModel;
        this.friendModel = friendModel;
    }

    public Map<String, Object> getFullUserInfo(String username) {
        User user = userModel.getUserByPhone(username);
        List<Group> groups = groupModel.getGroupsByUserId(userModel.getUidByAccount(username));
        return Map.of("userinfo", user, "groups", groups);
    }

    public Map<String, Object> getLimitUserInfo(Integer uid) {
        User user = userModel.getUserByUid(uid);
        if (user==null) return null;
        return Map.of("user_id", user.getUid(), "avatarUrl", Optional.ofNullable(user.getProfileURL()), "account", user.getAccount());
    }

    public Map<String, Object> getLimitUserInfo(String username) {
        User user = userModel.getUserByPhone(username);
        if (user==null) return null;
        return Map.of("user_id", user.getUid(), "avatarUrl", user.getProfileURL(), "account", user.getAccount());
    }

    public boolean checkInGroup(String username, Integer gid) {
        List<Group> groups = groupModel.getGroupsByUserId(userModel.getUidByAccount(username));
        return groups.stream().anyMatch(group -> group.getGId().equals(gid));
    }

    public boolean checkExist(String username) {
        return userModel.getUserByPhone(username) == null;
    }


}
