package hdu.homework.chat.modules.user.service;

import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.modules.user.model.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Service("userdetails")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.limitUserInfoByAccount(s);
        return new UserDetailsImpl(user);
    }

}
