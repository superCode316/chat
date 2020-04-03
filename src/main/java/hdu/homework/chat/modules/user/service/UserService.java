package hdu.homework.chat.modules.user.service;

import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.modules.user.model.UserModel;
import hdu.homework.chat.modules.user.repo.UserRepository;
import org.springframework.stereotype.Service;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Service
public class UserService {
    private UserModel userModel;
    private UserRepository userRepository;

    public UserService(UserModel userModel, UserRepository userRepository) {
        this.userModel = userModel;
        this.userRepository = userRepository;
    }

    public User getLimitUserInfo(String account) {
        return userModel.getLimitUserInfo(account);
    }

    public User getLimitUserInfo(Integer uid) {
        return userModel.getLimitUserInfo(uid);
    }

    public User getFullUserInfo(String account) {
        return userModel.getFullUserInfo(account);
    }

    public void saveUserProfile(User user) {
        userRepository.save(user);
    }
}
