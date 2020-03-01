package hdu.homework.chat.utils;

import hdu.homework.chat.entity.bean.response.Msg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResultUtil {
    public static ResponseEntity<Msg<?>> result(Integer status, Integer code, String msg) {
        return ResponseEntity.status(status).body(new Msg<String>(code, msg));
    }
    public static ResponseEntity<Msg<?>> result(HttpStatus status, Integer code, String msg) {
        return ResponseEntity.status(status).body(new Msg<String>(code, msg));
    }
    public static ResponseEntity<Msg<?>> error(HttpStatus status) {
        return result(status, -1, status.toString());
    }
    public static ResponseEntity<Msg<?>> error(HttpStatus status, String msg) {
        return result(status, -1, msg);
    }
    public static ResponseEntity<Msg<?>> error(String msg) {
        return result(500, -1, msg);
    }
    public static ResponseEntity<Msg<?>> error() {
        return error("error");
    }
    public static ResponseEntity<Msg<?>> success(Object obj) {
        return ResponseEntity.status(200).body(new Msg<>(0, "success", obj));
    }
    public static ResponseEntity<Msg<?>> success() {
        return success(null);
    }
}
