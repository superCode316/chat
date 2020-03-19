package hdu.homework.chat.modules.message.controller;

import hdu.homework.chat.annotations.FriendCheck;
import hdu.homework.chat.entity.bean.database.Message;
import hdu.homework.chat.entity.bean.response.Msg;
import hdu.homework.chat.entity.bean.response.swagger.Forbidden;
import hdu.homework.chat.entity.bean.response.swagger.MessagesResponse;
import hdu.homework.chat.entity.bean.response.swagger.SuccessResponse;
import hdu.homework.chat.modules.message.service.MessageService;
import hdu.homework.chat.modules.message.socket.WebSocket;
import hdu.homework.chat.modules.user.service.UserDetailsServiceImpl;
import hdu.homework.chat.modules.user.service.UserService;
import hdu.homework.chat.utils.DateUtils;
import hdu.homework.chat.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Api(tags = "信息管理接口")
@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {
    private MessageService service;
    private UserService userService;
    private UserDetailsServiceImpl userDetailsService;
    private RedisTemplate<String, String> redis;

    public MessageController(MessageService service, UserService userService, UserDetailsServiceImpl userDetailsService, RedisTemplate<String, String> redis) {
        this.service = service;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.redis = redis;
    }

    @ApiOperation("发送消息接口")
    @ApiResponses({
            @ApiResponse(code = 200, response = SuccessResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    @PostMapping("/send")
    @FriendCheck
    public ResponseEntity<Msg<?>> receiceMessage(Integer receiver, String content) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        try {
//            String receiverName = (String) userService.getLimitUserInfo(receiver).get("account");
//            WebSocket.send(receiverName, content);
//        } catch (Exception e) {
//            log.error(e.getLocalizedMessage());
//        }
        Message message1 = new Message(content, receiver);
        message1.setTime(DateUtils.getNowDateString());
        service.addMessage(username, message1);
        return ResultUtil.success();
    }

    @ApiOperation("获取用户和指定的用户消息的接口")
    @ApiResponses({
            @ApiResponse(code = 200, response = MessagesResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @FriendCheck(targetName = "receiver")
    public ResponseEntity<Msg<?>> getMessage(@RequestParam(defaultValue = "0") Integer offsetId, @RequestParam Integer receiver, @RequestParam(defaultValue = "0") Integer type) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Message> messages = service.getMessages(offsetId, receiver, username);
        return ResultUtil.success(messages);
    }

//    @Deprecated
//    @ApiOperation("获取用户发送的所有消息的接口")
//    @ApiResponses({
//            @ApiResponse(code = 200, response = MessagesResponse.class, message = "请求成功"),
//            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
//            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
//    })
//    @RequestMapping(value = "/sent", method = RequestMethod.GET)
//    public ResponseEntity<Msg<?>> sentMessage(@RequestParam(defaultValue = "") Integer receiver) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        List<Message> messages = service.getSentMessage(username);
//        return ResultUtil.success(messages);
//    }

//    @ApiOperation("获取群组消息的接口")
//    @ApiResponses({
//            @ApiResponse(code = 200, response = MessagesResponse.class, message = "请求成功"),
//            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
//            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
//    })
//    @RequestMapping(value = "/get-group", method = RequestMethod.GET)
//    public ResponseEntity<Msg<?>> getGroupMessage(@RequestParam Integer offsetId, @RequestParam Integer receiver, @RequestParam Integer type) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        RequestMessage body = new RequestMessage(offsetId, receiver, type);
//        if (!userService.checkInGroup(username, body.getReceiver()))
//            return ResultUtil.forbidden();
//        List<Message> messages = service.getMessages(body, null);
//        return ResultUtil.success(messages);
//    }

    @FriendCheck
    @RequestMapping(value = "/public-key", method = RequestMethod.GET)
    public ResponseEntity<Msg<?>> getPublicKey(@RequestParam(defaultValue = "-1", required = false) Integer fid) {
        String key;
        if (fid == -1)
            key = redis.opsForValue().get("server_public_key");
        else
            key = redis.opsForValue().get("user_public_key_"+fid);

        return ResultUtil.success(key);
    }

    @RequestMapping(value = "/public-key", method = RequestMethod.POST)
    public ResponseEntity<Msg<?>> postPublicKey(HttpServletRequest request) {
        String key = request.getParameter("key");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer uid = userDetailsService.getUidByUsername(username);
        redis.opsForValue().set("user_public_key_"+uid, key);
        return ResultUtil.success();
    }

    /**
     * TODO：消息加解密
     *
     */
}
