package hdu.homework.chat.modules.user.service;

import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.modules.user.model.UserRepository;
import org.springframework.stereotype.Service;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getLimitUserInfo(String account) {
        return userRepository.limitUserInfoByAccount(account);
    }

    public User getLimitUserInfo(Integer uid) {
        return userRepository.limitUserInfoById(uid);
    }

    public User getFullUserInfo(String account) {
        return userRepository.findByAccount(account);
    }

    public void saveUserProfile(User user) {
        userRepository.save(user);
    }
}
