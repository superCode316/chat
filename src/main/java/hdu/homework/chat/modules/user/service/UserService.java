package hdu.homework.chat.modules.user.service;

import hdu.homework.chat.entity.bean.database.Group;
import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.modules.groups.model.GroupModel;
//import hdu.homework.chat.modules.user.model.FriendModel;
import hdu.homework.chat.modules.user.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Service
public class UserService {
    private UserModel userModel;
//    private FriendModel friendModel;
    private GroupModel groupModel;

    public UserService(UserModel userModel, GroupModel groupModel) {
        this.userModel = userModel;
        this.groupModel = groupModel;
//        this.friendModel = friendModel;
    }

    public User getLimitUserInfo(String username) {
        return getLimitUserInfo(userModel.getUidByAccount(username));
    }

    public User getLimitUserInfo(Integer uid) {
        return userModel.getLimitUserInfo(uid);
    }

    public boolean checkInGroup(String username, Integer gid) {
        List<Group> groups = groupModel.getGroupsByUserId(userModel.getUidByAccount(username));
        return groups.stream().anyMatch(group -> group.getGId().equals(gid));
    }

    public boolean checkExist(String username) {
        return userModel.getUserByPhone(username) == null;
    }


}
