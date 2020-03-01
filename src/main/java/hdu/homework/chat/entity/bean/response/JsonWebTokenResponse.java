package hdu.homework.chat.entity.bean.response;

import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class JsonWebTokenResponse extends HashMap<String, Object> {
    public static JsonWebTokenResponse builder() {
        return new JsonWebTokenResponse();
    }
    public JsonWebTokenResponse token(String t) {
        put("token", t);
        return this;
    }
    public JsonWebTokenResponse expire(int e) {
        put("expire", e / 1000);
        return this;
    }
}
