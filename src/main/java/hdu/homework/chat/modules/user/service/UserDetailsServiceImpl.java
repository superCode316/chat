package hdu.homework.chat.modules.user.service;

import hdu.homework.chat.entity.bean.Group;
import hdu.homework.chat.entity.bean.User;
import hdu.homework.chat.modules.groups.model.GroupModel;
import hdu.homework.chat.modules.user.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userdetails")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserModel userModel;
    private GroupModel groupModel;

    public UserDetailsServiceImpl(UserModel userModel, GroupModel groupModel) {
        this.userModel = userModel;
        this.groupModel = groupModel;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userModel.getUserByPhone(s);
        return new UserDetailsImpl(user);
    }

    public Integer getUidByUsername(String username) {
        return userModel.getUserByPhone(username).getUid();
    }

}
