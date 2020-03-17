package hdu.homework.chat.modules.user.service;

import hdu.homework.chat.entity.bean.database.Friend;
import hdu.homework.chat.entity.bean.response.FriendInfo;
import hdu.homework.chat.entity.bean.response.FriendName;
import hdu.homework.chat.modules.user.model.FriendModel;
import hdu.homework.chat.modules.user.model.UserModel;
import hdu.homework.chat.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/17/2020
 */
@Service
public class FriendService {

    private UserModel userModel;
    private FriendModel friendModel;

    public FriendService(UserModel userModel, FriendModel friendModel) {
        this.userModel = userModel;
        this.friendModel = friendModel;
    }

    public void addFriend(String username, String friend) {
        Integer uid = userModel.getUidByAccount(username);
        List<Friend> apply = getApplies(username);
        if (apply.stream().anyMatch(f -> f.getUserId().equals(uid) && f.getIsAgree().equals(1)))
            return;
        friendModel.addFriend(userModel.getUidByAccount(friend), uid);
    }

    public List<FriendInfo> getFriends(String username) {
        return friendModel.getFriends(userModel.getUidByAccount(username));
    }

    public List<Friend> getApplies(String username) {
        return friendModel.getApplies(userModel.getUidByAccount(username));
    }

    /**
     *
     * @param username 被申请人
     * @param fid 主动申请人
     * @param status
     */
    public boolean applyStatus(String username, Integer fid, Integer status) {
        List<Friend> a = getApplies(username);
        if (getApplies(username).stream().filter(user->user.getUserId().equals(fid)).noneMatch(user-> user.getIsAgree().equals(1))) return false;
        friendModel.updateApply(status, fid, userModel.getUidByAccount(username));
        return true;
    }

    public boolean isFriend(String username, String friend) {
        Integer fid = userModel.getUidByAccount(friend);
        return isFriend(username, fid);
    }

    public boolean isFriend(String username, Integer fid) {
        for (FriendInfo f : getFriends(username)) {
            if (f.getUId().equals(fid))
                return true;
        }
        return false;
    }
}
