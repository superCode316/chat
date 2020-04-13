package hdu.homework.chat.modules.groups.service;

import hdu.homework.chat.entity.bean.database.GroupInfo;
import hdu.homework.chat.entity.bean.database.GroupUsers;
import hdu.homework.chat.modules.groups.model.GroupRepository;
import hdu.homework.chat.modules.groups.model.GroupUserRepository;
import hdu.homework.chat.modules.user.model.UserRepository;
import hdu.homework.chat.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Service
public class GroupService {

    private GroupUserRepository guRepository;
    private UserRepository userRepository;
    private GroupRepository groupRepository;

    public GroupService(GroupUserRepository guRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.guRepository = guRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public Optional<GroupInfo> getGroupByGid(Integer gid) {
        GroupInfo groupInfo = groupRepository.getGroupInfoBygId(gid);
        return Optional.ofNullable(groupInfo);
    }

    public GroupInfo addGroup(String account, GroupInfo g) {
        g.setAdminId(userRepository.getUidByAccount(account));
        g.setCreateTime(DateUtils.getNowDateString());
        return groupRepository.save(g);
    }

    public void joinGroup(Integer gid, String account) {
        guRepository.save(new GroupUsers(gid, userRepository.getUidByAccount(account), DateUtils.getNowDateString()));
    }

    public List<GroupInfo> getGroupsByUserName(String account) {
        return groupRepository.groupInfosByAccount(account);
    }

    public List<GroupInfo> searchGroups(String name) {
        return groupRepository.getGroupInfosByNameLike(name);
    }

    public List<Integer> getUsersInGroup(Integer gid) {
        return guRepository.usersInGroup(gid);
    }

}
