package hdu.homework.chat.entity.bean;

import lombok.Data;

@Data
public class Msg<T> {
    private int code;
    private String msg;
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
