package hdu.homework.chat.modules.message.controller;

import hdu.homework.chat.entity.bean.Message;
import hdu.homework.chat.entity.bean.request.MessageBody;
import hdu.homework.chat.entity.bean.request.RequestMessage;
import hdu.homework.chat.entity.bean.response.Msg;
import hdu.homework.chat.entity.bean.response.swagger.Forbidden;
import hdu.homework.chat.entity.bean.response.swagger.GroupsResponse;
import hdu.homework.chat.entity.bean.response.swagger.MessagesResponse;
import hdu.homework.chat.entity.bean.response.swagger.SuccessResponse;
import hdu.homework.chat.entity.factory.MessageFactory;
import hdu.homework.chat.modules.message.service.MessageService;
import hdu.homework.chat.modules.user.service.UserDetailsServiceImpl;
import hdu.homework.chat.modules.user.service.UserService;
import hdu.homework.chat.utils.DateUtils;
import hdu.homework.chat.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "信息管理接口")
@RestController
@RequestMapping("/message")
public class MessageController {
    private MessageService service;
    private UserService userService;

    public MessageController(MessageService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @ApiOperation("发送消息接口")
    @ApiResponses({
            @ApiResponse(code = 200, response = SuccessResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    @PostMapping("/send")
    public ResponseEntity<Msg<?>> receiceMessage(@RequestBody MessageBody message) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Message message1 = MessageFactory.instance()
                .to(message.getTo(), message.getType())
                .content(message.getContent())
                .build();
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
    public ResponseEntity<Msg<?>> getMessage(@RequestBody RequestMessage body) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Message> messages = service.getMessages(body, username);
        return ResultUtil.success(messages);
    }

    @ApiOperation("获取用户发送的所有消息的接口")
    @ApiResponses({
            @ApiResponse(code = 200, response = MessagesResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    @RequestMapping(value = "/sent", method = RequestMethod.GET)
    public ResponseEntity<Msg<?>> sentMessage() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Message> messages = service.getSentMessage(username);
        return ResultUtil.success(messages);
    }

    @ApiOperation("获取群组消息的接口")
    @ApiResponses({
            @ApiResponse(code = 200, response = MessagesResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    @RequestMapping(value = "/get-group", method = RequestMethod.GET)
    public ResponseEntity<Msg<?>> getGroupMessage(@RequestBody RequestMessage body) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!userService.checkInGroup(username, body.getReceiver()))
            return ResultUtil.forbidden();
        List<Message> messages = service.getMessages(body, null);
        return ResultUtil.success(messages);
    }
}
