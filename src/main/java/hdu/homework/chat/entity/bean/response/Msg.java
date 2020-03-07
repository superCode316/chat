package hdu.homework.chat.entity.bean.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel()
public class Msg<T> {
    private int code;
    private String msg;
    @ApiModelProperty(name = "返回数据")
    private T data;

    public Msg(Integer code) {
        this.code = code;
    }

    public Msg(Integer code, String msg, T obj) {
        this.code = code;
        this.msg = msg;
        this.data = obj;
    }

    public Msg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
