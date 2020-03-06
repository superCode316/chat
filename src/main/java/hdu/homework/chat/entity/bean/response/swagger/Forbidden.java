package hdu.homework.chat.entity.bean.response.swagger;

import hdu.homework.chat.entity.bean.response.Msg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class Forbidden extends Msg<String>{
    @ApiModelProperty(name = "消息", value = "拒绝请求", dataType = "string")
    private String msg;

    public Forbidden(Integer code) {
        super(code);
    }
}
