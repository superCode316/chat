package hdu.homework.chat.modules.groups.service;

import hdu.homework.chat.entity.bean.database.Group;
import hdu.homework.chat.modules.groups.model.GroupModel;
import hdu.homework.chat.modules.groups.model.GroupUserModel;
import hdu.homework.chat.modules.user.service.UserDetailsServiceImpl;
import hdu.homework.chat.modules.user.service.UserService;
import hdu.homework.chat.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Service
public class GroupService {
    private GroupModel groupModel;
    private GroupUserModel guModel;
    private UserDetailsServiceImpl userModel;
    private UserService service;

    public GroupService(GroupModel groupModel, GroupUserModel guModel, UserDetailsServiceImpl userModel, UserService service) {
        this.groupModel = groupModel;
        this.guModel = guModel;
        this.userModel = userModel;
        this.service = service;
    }

    public Group getGroupByGid(Integer gid) {
        return groupModel.getGroupById(gid);
    }

    public Group getGroupByName(String name) {
        return groupModel.getGroupByName(name);
    }

    public void addGroup(String username, Group g) {
        g.setAdminId(userModel.getUidByUsername(username));
        g.setCreateTime(DateUtils.getNowDateString());
        groupModel.addGroup(g);
    }

    public void joinGroup(Integer gid, String username) throws SQLException {
        guModel.join(gid, userModel.getUidByUsername(username), DateUtils.getNowDateString());
    }

    public List<Group> getGroupsByUserName(String username) {
        return groupModel.getGroupsByUserId(userModel.getUidByUsername(username));
    }

    public List<Group> searchGroups(String name) {
        return groupModel.searchGroupsByName(name);
    }

    public List<Map<String, Object>> getUsersInGroup(Integer gid) {
        List<Integer> users = guModel.getUsers(gid);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Integer i : users) {
            result.add(service.getLimitUserInfo(i));
        }
        return result;
    }
}
