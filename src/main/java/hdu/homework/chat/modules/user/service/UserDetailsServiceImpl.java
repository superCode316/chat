package hdu.homework.chat.modules.user.service;

import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.modules.groups.model.GroupModel;
import hdu.homework.chat.modules.user.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Service("userdetails")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserModel userModel;

    public UserDetailsServiceImpl(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userModel.getUserByPhone(s);
        return new UserDetailsImpl(user);
    }

}
