package hdu.homework.chat.modules.user.controller;

import hdu.homework.chat.entity.bean.response.Friend;
import hdu.homework.chat.entity.bean.response.JsonWebTokenResponse;
import hdu.homework.chat.entity.bean.response.Msg;
import hdu.homework.chat.modules.groups.service.GroupService;
import hdu.homework.chat.modules.user.service.UserDetailsServiceImpl;
import hdu.homework.chat.modules.user.service.UserService;
import hdu.homework.chat.utils.ResultUtil;
import hdu.homework.chat.entity.bean.request.UserPost;
import hdu.homework.chat.modules.user.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AuthenticationService service;
    private UserService userService;
    private UserDetailsServiceImpl userDetailsService;
    private GroupService groupService;
    public UserController(AuthenticationService service, UserService userService, UserDetailsServiceImpl userDetailsService, GroupService groupService) {
        this.service = service;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.groupService = groupService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Msg<?>> postLogin(@RequestBody UserPost user) {
        String loginSuccess = service.login(user.getUsername(), user.getPassword());
        if (loginSuccess==null){
            return ResultUtil.result(HttpStatus.FORBIDDEN, 1001,  "yonghuminghuomimachucuo");
        } else {
            service.logUser(user.getUsername());
            Object token = JsonWebTokenResponse.builder()
                    .token(loginSuccess)
                    .expire(service.getExpire());
            Map<String, Object> map = userService.getFullUserInfo(user.getUsername());
            return ResultUtil.success(Map.of("token", token, "groups", map.get("groups")));
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Msg<?>> postRegister(@RequestBody UserPost user) {

        String checkRegister = service.checkRegister(user);

        if (checkRegister != null)
            return ResultUtil.error(HttpStatus.BAD_REQUEST, checkRegister);

        service.addUser(user);

        return ResultUtil.success();
    }

    @RequestMapping(value = "/add-friend", method = RequestMethod.POST)
    public ResponseEntity<Msg<?>> addFriend(@RequestBody UserPost user) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userService.checkExist(user.getUsername()))
            return ResultUtil.error("没有此用户");
        userService.addFriend(username, user.getUsername());
        return ResultUtil.success();
    }

    @RequestMapping("/friends")
    public ResponseEntity<Msg<?>> getFriends() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Friend> friends = userService.getFriends(username);
        return ResultUtil.success(friends);
    }
}
