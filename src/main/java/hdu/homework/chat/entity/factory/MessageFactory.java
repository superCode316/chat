package hdu.homework.chat.entity.factory;

import hdu.homework.chat.entity.bean.Message;
import hdu.homework.chat.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MessageFactory {
    private static final int PEOPLE = 0;
    private static final int GROUP = 0;
    public static class MessageBuilder {
        private Message message;
        public MessageBuilder() {
            this.message = new Message();
        }
        public MessageBuilder from(Integer sid) {
            message.setSenderIDl(sid);
            return this;
        }
        public MessageBuilder to(Integer tid, int type) {
            if (type == PEOPLE) {
                message.setRecieverID(tid);
            } else {
                message.setGroupID(tid);
            }
            return this;
        }
        public MessageBuilder content(String content) {
            message.setContent(content);
            return this;
        }
        public Message build() {
            message.setTime(DateUtils.getNowDateString());
            return message;
        }
    }
    public static MessageBuilder instance() {
        return new MessageBuilder();
    }
}
