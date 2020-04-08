package hdu.homework.chat.modules.user.controller;

import hdu.homework.chat.entity.bean.database.GroupInfo;
import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.entity.bean.response.Msg;
import hdu.homework.chat.entity.bean.response.swagger.Forbidden;
import hdu.homework.chat.entity.bean.response.swagger.SuccessResponse;
import hdu.homework.chat.modules.message.utils.WebSocket;
import hdu.homework.chat.modules.user.service.AuthenticationService;
import hdu.homework.chat.modules.groups.service.GroupService;
import hdu.homework.chat.modules.user.service.UserService;
import hdu.homework.chat.utils.RedisUtil;
import hdu.homework.chat.utils.ResultUtil;
import hdu.homework.chat.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * created by 钱曹宇@supercode on 3/15/2020
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;
    private UserService userService;
    private RedisUtil redisUtil;
    private GroupService groupService;

    public AuthenticationController(AuthenticationService service, UserService userService, RedisUtil redisUtil, GroupService groupService) {
        this.service = service;
        this.userService = userService;
        this.redisUtil = redisUtil;
        this.groupService = groupService;
    }

    @ApiOperation(httpMethod = "POST", value = "用户登录接口", notes = "用户登录的返回数据。返回数据中的token需要保存，失效时间在数据中。在除了注册和登录请求意外的请求中，需要将token放在请求头中，格式为 x-access-token:TOKEN")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    public ResponseEntity<Msg<?>> postLogin(String username, String password, HttpServletResponse response) {
        String loginSuccess = service.login(username, password);
        if (loginSuccess==null){
            return ResultUtil.result(HttpStatus.FORBIDDEN, 1001,  "用户名或密码出错");
        } else {

            User userInfo = userService.getLimitUserInfo(username);
            List<GroupInfo> groupInfoList = groupService.getGroupsByUserName(username);
            Cookie cookie = new Cookie("x-access-token", loginSuccess);
            cookie.setMaxAge(60*60);
            cookie.setPath("/");
            response.addCookie(cookie);

            String ticket = StringUtils.randomString(username, password);
//            redisUtil.set(ticket, userInfo.getUId());
            WebSocket.setUserTicket(ticket, String.valueOf(userInfo.getUId()));
            return ResultUtil.success(
                    Map.of("groups", groupInfoList,
                            "userid", userInfo.getUId(),
                            "ticket", ticket)
            );
        }
    }

    @ApiOperation(httpMethod = "POST", value = "用户注册接口")
    @ApiResponses({
            @ApiResponse(code = 200, response = SuccessResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Msg<?>> postRegister(String username, String password) {

        Optional<String> result = service.register(username, password);
        return result
                .map(s -> ResultUtil.error(HttpStatus.BAD_REQUEST, s))
                .orElseGet(ResultUtil::success);

    }
}
