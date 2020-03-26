package hdu.homework.chat.modules.user.controller;

import hdu.homework.chat.annotations.FriendCheck;
import hdu.homework.chat.entity.bean.database.Friend;
import hdu.homework.chat.entity.bean.response.FriendName;
import hdu.homework.chat.entity.bean.response.Msg;
import hdu.homework.chat.entity.bean.response.swagger.Forbidden;
import hdu.homework.chat.entity.bean.response.swagger.FriendResponse;
import hdu.homework.chat.entity.bean.response.swagger.SuccessResponse;
import hdu.homework.chat.modules.user.service.FriendService;
import hdu.homework.chat.modules.user.service.UserService;
import hdu.homework.chat.utils.ResultUtil;
import hdu.homework.chat.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.tomcat.util.http.ResponseUtil;
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
    private List<Integer> applyStatus = List.of(2,3);
    private RedisTemplate<String, Object> redisTemplate;
    private UserService userService;
    private FriendService friendService;
    public UserController(RedisTemplate<String, Object> redisTemplate, UserService userService, FriendService friendService) {
        this.redisTemplate = redisTemplate;
        this.userService = userService;
        this.friendService = friendService;
    }

//    @ApiOperation(httpMethod = "POST", value = "添加好友接口")
//    @ApiResponses({
//            @ApiResponse(code = 200, response = SuccessResponse.class, message = "请求成功"),
//            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
//            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
//    })
//    @RequestMapping(value = "/add-friend", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<Msg<?>> addFriend(String friend) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        if (userService.checkExist(friend))
//            return ResultUtil.error(HttpStatus.BAD_REQUEST, "没有此用户");
//        if (friendService.isFriend(username, friend))
//            return ResultUtil.error(HttpStatus.BAD_REQUEST, "你和对方已经是朋友");
//        friendService.addFriend(username, friend);
//        return ResultUtil.success();
//    }

//    @ApiOperation(httpMethod = "GET", value = "获取好友接口")
//    @ApiResponses({
//            @ApiResponse(code = 200, response = FriendResponse.class, message = "请求成功"),
//            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
//            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
//    })
//    @RequestMapping("/friends")
//    public ResponseEntity<Msg<?>> getFriends() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Object friends = friendService.getFriends(username);
//        return ResultUtil.success(friends);
//    }

//    @RequestMapping(value = "/status", method = RequestMethod.GET)
//    @FriendCheck
//    public ResponseEntity<Msg<?>> getFriendStatus(@RequestParam Integer fid) {
//        Object isOnline = redisTemplate.opsForValue().get(String.valueOf(fid));
//        if (isOnline == null)
//            isOnline = Boolean.FALSE;
//        return ResultUtil.success(isOnline);
//    }

//    @RequestMapping("/search")
//    public ResponseEntity<Msg<?>> searchFriends(@RequestParam String name) {
//        Map<String, Object> userSearch = userService.getLimitUserInfo(name);
//        return ResultUtil.success(userSearch);
//    }

    @RequestMapping("/info")
    public ResponseEntity<Msg<?>> userBaseInfo(@RequestParam String uid) {
        if (!StringUtils.isDigit(uid))
            return ResultUtil.error(HttpStatus.BAD_REQUEST, "用户id不正确");
        Map<String, Object> userInfo = userService.getLimitUserInfo(Integer.parseInt(uid));
        return ResultUtil.success(userInfo);
    }

//    @RequestMapping("/get-applies")
//    public ResponseEntity<Msg<?>> getApplies() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        List<Friend> applies = friendService.getApplies(username);
//        return ResultUtil.success(applies);
//    }
//
//    @RequestMapping(value = "/manage-apply", method = RequestMethod.POST)
//    public ResponseEntity<Msg<?>> manage(Integer fid, Integer isAgree) {
//        if (!applyStatus.contains(isAgree))
//            return ResultUtil.error(HttpStatus.BAD_REQUEST,"参数错误");
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        if (!friendService.applyStatus(username, fid, isAgree))
//            return ResultUtil.error(HttpStatus.BAD_REQUEST);
//        return ResultUtil.success();
//    }

}
