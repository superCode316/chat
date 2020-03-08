package hdu.homework.chat.entity.bean.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Getter
@ApiModel("消息实体")
public class MessageBody {
    @ApiModelProperty(value = "发送对象ID", required = true, dataType = "number")
    private Integer to;
    @ApiModelProperty(value = "发送内容", required = true, dataType = "string")
    private String content;
    @ApiModelProperty(value = "发送对象类型（0位用户，1位群组）", required = true, dataType = "number")
    private Integer type;
}
