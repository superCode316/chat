package hdu.homework.chat.modules.groups.service;

import hdu.homework.chat.entity.bean.database.Group;
import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.modules.groups.model.GroupModel;
import hdu.homework.chat.modules.groups.model.GroupUserModel;
import hdu.homework.chat.modules.user.model.UserModel;
import hdu.homework.chat.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Service
public class GroupService {
    private GroupModel groupModel;
    private GroupUserModel guModel;
    private UserModel userModel;

    public GroupService(GroupModel groupModel, GroupUserModel guModel, UserModel userModel) {
        this.groupModel = groupModel;
        this.guModel = guModel;
        this.userModel = userModel;
    }

    public Optional<Group> getGroupByGid(Integer gid) {
        Group group = groupModel.getGroupById(gid);
        return Optional.ofNullable(group);
    }

    public void addGroup(String username, Group g) {
        g.setAdminId(userModel.getUidByAccount(username));
        g.setCreateTime(DateUtils.getNowDateString());
        groupModel.addGroup(g);
    }

    public void joinGroup(Integer gid, String username) throws SQLException {
        guModel.join(gid, userModel.getUidByAccount(username), DateUtils.getNowDateString());
    }

    public List<Group> getGroupsByUserName(String username) {
        return groupModel.getGroupsByUserId(userModel.getUidByAccount(username));
    }

    public List<Group> searchGroups(String name) {
        return groupModel.searchGroupsByName(name);
    }

    public List<User> getUsersInGroup(Integer gid) {
        return guModel.getUsers(gid).stream().map(i->userModel.getLimitUserInfo(i)).collect(Collectors.toList());
    }

}
