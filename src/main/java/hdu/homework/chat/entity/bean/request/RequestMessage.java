package hdu.homework.chat.entity.bean.request;

import lombok.Getter;

@Getter
public class RequestMessage {
    private Integer offsetId;
    private Integer receiver;
    private Integer type;
}
