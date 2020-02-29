package hdu.homework.chat.entity.bean.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPost {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

}
