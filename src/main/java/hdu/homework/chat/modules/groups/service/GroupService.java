package hdu.homework.chat.modules.groups.service;

import hdu.homework.chat.entity.bean.database.Group;
import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.modules.groups.model.GroupModel;
import hdu.homework.chat.modules.groups.model.GroupUserModel;
import hdu.homework.chat.modules.user.model.UserRepository;
import hdu.homework.chat.utils.DateUtils;
import org.springframework.stereotype.Service;

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
    private UserRepository userRepository;

    public GroupService(GroupModel groupModel, GroupUserModel guModel, UserRepository userRepository) {
        this.groupModel = groupModel;
        this.guModel = guModel;
        this.userRepository = userRepository;
    }

    public Optional<Group> getGroupByGid(Integer gid) {
        Group group = groupModel.getGroupById(gid);
        return Optional.ofNullable(group);
    }

    public void addGroup(String account, Group g) {
        g.setAdminId(userRepository.getUidByAccount(account));
        g.setCreateTime(DateUtils.getNowDateString());
        groupModel.addGroup(g);
    }

    public void joinGroup(Integer gid, String account) {
        guModel.join(gid, userRepository.getUidByAccount(account), DateUtils.getNowDateString());
    }

    public List<Group> getGroupsByUserName(String account) {
        return groupModel.getGroupsByUserId(userRepository.getUidByAccount(account));
    }

    public List<Group> searchGroups(String name) {
        return groupModel.searchGroupsByName(name);
    }

    public List<User> getUsersInGroup(Integer gid) {
        return guModel.getUsers(gid).stream().map(i->userRepository.limitUserInfoById(i)).collect(Collectors.toList());
    }

}
