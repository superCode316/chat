package hdu.homework.chat.entity.bean.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Getter
@Setter
@ApiModel("用户实体")
public class UserPost {
    @JsonProperty("username")
    @ApiModelProperty(value = "用户名", required = true, dataType = "string")
    private String username;
    @ApiModelProperty(value = "用户密码，添加好友的接口不用", required = false, dataType = "string")
    @JsonProperty("password")
    private String password;

}
