package hdu.homework.chat.entity.bean.response.swagger;

import hdu.homework.chat.entity.bean.response.Msg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel
public class SuccessResponse extends Msg<String> {
    public SuccessResponse(Integer code) {
        super(code);
    }
    @ApiModelProperty(name = "OK", value = "消息")
    private String msg;
}
