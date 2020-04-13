package hdu.homework.chat.modules.user.controller;

import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.entity.bean.response.Msg;
//import hdu.homework.chat.modules.user.service.FriendService;
import hdu.homework.chat.modules.user.service.UserService;
import hdu.homework.chat.utils.ResultUtil;
import hdu.homework.chat.utils.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/info")
    public ResponseEntity<Msg<?>> userBaseInfo(@RequestParam String uid) {
        if (!StringUtils.isDigit(uid))
            return ResultUtil.error(HttpStatus.BAD_REQUEST, "用户id不正确");
        User userInfo = userService.getLimitUserInfo(Integer.parseInt(uid));
        return ResultUtil.success(userInfo);
    }

    @RequestMapping("/profile")
    public ResponseEntity<Msg<?>> userProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User userInfo = userService.getFullUserInfo(username);
        return ResultUtil.success(userInfo);
    }

    @RequestMapping(value = "/update-profile", method = RequestMethod.POST)
    public ResponseEntity<Msg<?>> updateProfile(@RequestBody(required = false)MultipartFile file,
                                                String name, String sex,
                                                String date, String pro,
                                                String city, String autoGraph) {
        String account = SecurityContextHolder.getContext().getAuthentication().getName();
        User userInfo = userService.getFullUserInfo(account);

        userInfo.setName(name);
        userInfo.setBirth(date);
        userInfo.setSign(autoGraph);
        userInfo.setProvince(pro);
        userInfo.setCity(city);

        boolean requireFileChange = false;
        if (file != null) {
            String fileEncode = StringUtils.encodeFile(file.getOriginalFilename());
            if (fileEncode.equals(userInfo.getProfileUrl()))
                requireFileChange = true;
            userInfo.setProvince(fileEncode);
            saveFile();
        }
        userService.saveUserProfile(userInfo);
        return ResultUtil.success(userInfo.getUId());
    }

    private void saveFile() {}
}
