package hdu.homework.chat.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Setter;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Data
@ApiModel
public class TokenEntity {
    @ApiModelProperty(name = "token", dataType = "string")
    private String token;
    @ApiModelProperty(name = "失效时间", dataType = "number")
    private Integer expire;
}
