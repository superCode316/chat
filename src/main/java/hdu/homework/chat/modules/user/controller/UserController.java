package hdu.homework.chat.modules.user.controller;

import hdu.homework.chat.entity.bean.response.Friend;
import hdu.homework.chat.entity.bean.response.JsonWebTokenResponse;
import hdu.homework.chat.entity.bean.response.Msg;
import hdu.homework.chat.entity.bean.response.swagger.Forbidden;
import hdu.homework.chat.entity.bean.response.swagger.FriendResponse;
import hdu.homework.chat.entity.bean.response.swagger.LoginResponse;
import hdu.homework.chat.entity.bean.response.swagger.SuccessResponse;
import hdu.homework.chat.modules.groups.service.GroupService;
import hdu.homework.chat.modules.user.service.UserDetailsServiceImpl;
import hdu.homework.chat.modules.user.service.UserService;
import hdu.homework.chat.utils.ResultUtil;
import hdu.homework.chat.entity.bean.request.UserPost;
import hdu.homework.chat.modules.user.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    private final AuthenticationService service;
    private UserService userService;
    private UserDetailsServiceImpl userDetailsService;
    private GroupService groupService;
    private RedisTemplate<String, String> redis;
    public UserController(AuthenticationService service, UserService userService, UserDetailsServiceImpl userDetailsService, GroupService groupService, RedisTemplate<String, String> redis) {
        this.service = service;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.groupService = groupService;
        this.redis = redis;
    }

    @ApiOperation(httpMethod = "POST", value = "用户登录接口", notes = "用户登录的返回数据。返回数据中的token需要保存，失效时间在数据中。在除了注册和登录请求意外的请求中，需要将token放在请求头中，格式为 x-access-token:TOKEN")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 200, response = LoginResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    public ResponseEntity<Msg<?>> postLogin(@RequestBody UserPost user) {
        String loginSuccess = service.login(user.getUsername(), user.getPassword());
        if (loginSuccess==null){
            return ResultUtil.result(HttpStatus.FORBIDDEN, 1001,  "用户名或密码出错");
        } else {
            service.logUser(user.getUsername());
            Object token = JsonWebTokenResponse.builder()
                    .token(loginSuccess)
                    .expire(service.getExpire());
            Map<String, Object> map = userService.getFullUserInfo(user.getUsername());
            return ResultUtil.success(Map.of("token", token, "groups", map.get("groups")));
        }
    }

    @ApiOperation(httpMethod = "POST", value = "用户注册接口")
    @ApiResponses({
            @ApiResponse(code = 200, response = SuccessResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Msg<?>> postRegister(@RequestBody UserPost user) {

        String checkRegister = service.checkRegister(user);

        if (checkRegister != null)
            return ResultUtil.error(HttpStatus.BAD_REQUEST, checkRegister);

        service.addUser(user);

        return ResultUtil.success();
    }

    @ApiOperation(httpMethod = "POST", value = "添加好友接口")
    @ApiResponses({
            @ApiResponse(code = 200, response = SuccessResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    @RequestMapping(value = "/add-friend", method = RequestMethod.POST)
    public ResponseEntity<Msg<?>> addFriend(@RequestBody UserPost user) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userService.checkExist(user.getUsername()))
            return ResultUtil.error("没有此用户");
        userService.addFriend(username, user.getUsername());
        return ResultUtil.success();
    }

    @ApiOperation(httpMethod = "GET", value = "查找好友接口")
    @ApiResponses({
            @ApiResponse(code = 200, response = FriendResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    @RequestMapping("/friends")
    public ResponseEntity<Msg<?>> getFriends() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Friend> friends = userService.getFriends(username);
        return ResultUtil.success(friends);
    }
}
