package hdu.homework.chat.entity.bean.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Getter
@ApiModel("请求获取消息")
public class RequestMessage {
    @ApiModelProperty(value = "起始消息id值，没有则默认为0", required = false, dataType = "number")
    private Integer offsetId;
    @ApiModelProperty(value = "消息接收者", required = true, dataType = "number")
    private Integer receiver;
    @ApiModelProperty(value = "消息类型，0为个人，1为群组", required = true, dataType = "number")
    private Integer type;
}
