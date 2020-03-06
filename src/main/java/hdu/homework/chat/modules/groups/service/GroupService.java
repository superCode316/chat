package hdu.homework.chat.modules.groups.service;

import hdu.homework.chat.entity.bean.Group;
import hdu.homework.chat.modules.groups.model.GroupModel;
import hdu.homework.chat.modules.groups.model.GroupUserModel;
import hdu.homework.chat.modules.user.service.UserDetailsServiceImpl;
import hdu.homework.chat.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private GroupModel groupModel;
    private GroupUserModel guModel;
    private UserDetailsServiceImpl userModel;

    public GroupService(GroupModel groupModel, GroupUserModel guModel, UserDetailsServiceImpl userModel) {
        this.groupModel = groupModel;
        this.guModel = guModel;
        this.userModel = userModel;
    }

    public Group getGroupByGid(Integer gid) {
        return groupModel.getGroupById(gid);
    }

    public Group getGroupByName(String name) {
        return groupModel.getGroupByName(name);
    }

    public void addGroup(String username, Group g) {
        g.setAdminID(userModel.getUidByUsername(username));
        g.setCreaTime(DateUtils.getNowDateString());
        groupModel.addGroup(g);
    }

    public void joinGroup(Integer gid, String username) {
        guModel.join(gid, userModel.getUidByUsername(username), DateUtils.getNowDateString());
    }

    public List<Group> getGroupsByUserName(String username) {
        return groupModel.getGroupsByUserName(username);
    }
}
