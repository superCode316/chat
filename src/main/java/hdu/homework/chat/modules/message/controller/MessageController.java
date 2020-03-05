package hdu.homework.chat.modules.message.controller;

import hdu.homework.chat.entity.bean.Message;
import hdu.homework.chat.entity.bean.request.MessageBody;
import hdu.homework.chat.entity.bean.request.RequestMessage;
import hdu.homework.chat.entity.bean.response.Msg;
import hdu.homework.chat.entity.factory.MessageFactory;
import hdu.homework.chat.modules.message.service.MessageService;
import hdu.homework.chat.modules.user.service.UserDetailsServiceImpl;
import hdu.homework.chat.modules.user.service.UserService;
import hdu.homework.chat.utils.DateUtils;
import hdu.homework.chat.utils.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    private MessageService service;
    private UserService userService;

    public MessageController(MessageService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

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

    @RequestMapping("/get")
    public ResponseEntity<Msg<?>> getMessage(@RequestBody RequestMessage body) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Message> messages = service.getMessages(body, username);
        return ResultUtil.success(messages);
    }

    @RequestMapping("/sent")
    public ResponseEntity<Msg<?>> sentMessage() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Message> messages = service.getSentMessage(username);
        return ResultUtil.success(messages);
    }

    @RequestMapping("/get-group")
    public ResponseEntity<Msg<?>> getGroupMessage(@RequestBody RequestMessage body) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!userService.checkInGroup(username, body.getReceiver()))
            return ResultUtil.forbidden();
        List<Message> messages = service.getMessages(body, null);
        return ResultUtil.success(messages);
    }
}
