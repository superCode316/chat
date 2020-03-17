package hdu.homework.chat.entity.bean.response.swagger;

import hdu.homework.chat.entity.bean.database.Group;
import hdu.homework.chat.entity.bean.security.TokenEntity;
import hdu.homework.chat.entity.bean.response.Msg;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel()
public class LoginResponse extends Msg<TokenEntity> {

    public LoginResponse(Integer code) {
        super(code);
    }
}
class Login {
    private TokenEntity token;
    private List<Group> groups;
}
