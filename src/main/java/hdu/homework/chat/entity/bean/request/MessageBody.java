package hdu.homework.chat.entity.bean.request;

import lombok.Getter;

@Getter
public class MessageBody {
    private Integer to;
    private String content;
    private Integer type;
}
