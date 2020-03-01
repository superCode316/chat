package hdu.homework.chat.modules.user.service;

import hdu.homework.chat.entity.bean.User;
import hdu.homework.chat.modules.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
