package hdu.homework.chat.modules.message.controller;

import com.google.gson.Gson;
import hdu.homework.chat.annotations.GroupCheck;
import hdu.homework.chat.entity.bean.database.Messages;
import hdu.homework.chat.entity.bean.response.Msg;
import hdu.homework.chat.entity.bean.response.swagger.Forbidden;
import hdu.homework.chat.entity.bean.response.swagger.MessagesResponse;
import hdu.homework.chat.entity.bean.response.swagger.SuccessResponse;
import hdu.homework.chat.modules.message.service.MessageService;
import hdu.homework.chat.modules.message.utils.Producer;
import hdu.homework.chat.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private Gson gson;
    private Producer producer;
    public MessageController(MessageService service, Producer producer) {
        this.service = service;
        this.producer = producer;
        this.gson = new Gson();
    }

    @ApiOperation("发送消息接口")
    @ApiResponses({
            @ApiResponse(code = 200, response = SuccessResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    @PostMapping("/send")
    @GroupCheck(checkIn = true)
    public ResponseEntity<Msg<?>> receiveMessage(Integer receiver, String content, Integer senderId) {
        String json = gson.toJson(new Messages(senderId, content, receiver));
        producer.sendMessage(json);
//        service.sendMessage(receiver, content);
        return ResultUtil.success();
    }

    @ApiOperation("获取群组消息的接口")
    @ApiResponses({
            @ApiResponse(code = 200, response = MessagesResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    @RequestMapping(value = "/get-group", method = RequestMethod.GET)
    @GroupCheck(checkIn = true)
    public ResponseEntity<Msg<?>> getGroupMessage(@RequestParam Integer receiver) {
        List<Messages> messages = service.getGroupMessages(receiver);
        return ResultUtil.success(messages);
    }
//
//    /**
//     * TODO：消息加解密
//     *
//     */
}
