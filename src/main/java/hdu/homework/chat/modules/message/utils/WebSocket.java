package hdu.homework.chat.modules.message.utils;

import com.google.gson.Gson;
import hdu.homework.chat.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created by 钱曹宇@supercode on 3/15/2020
 */
@ServerEndpoint(value = "/websocket/{ticket}")
@Slf4j
@Component
public class WebSocket {
    private String userid;
    private Gson gson = new Gson();
    public static ConcurrentHashMap<String, WebSocket> webSocketSet = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, String> userTickets = new ConcurrentHashMap<>();

    private static ApplicationContext applicationContext;
    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocket.applicationContext = applicationContext;
    }

    public static void send(String username, String msg, Integer sender, Integer receiver) {
        if (!webSocketSet.containsKey(username))
            return;
        try {
            webSocketSet.get(username).sendMessage("{\"content\":\""+msg+"\",\"senderId\":\""+sender+"\",\"toId\":\""+receiver+"\"}");
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public static void setUserTicket(String ticket, String userid) {
        userTickets.put(ticket, userid);
    }

    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("ticket") String ticket) {
        this.session = session;
//        userid = String.valueOf(redisUtil.get(ticket));
        userid = userTickets.get(ticket);
        if (userid == null) {
            this.onClose();
            return;
        };
        webSocketSet.put(userid, this);
    }

    @OnClose
    public void onClose() {
        if (this.userid != null)
            webSocketSet.remove(this.userid);
    }

    @OnMessage
    public void onMessage(String message, Session session) {

    }

     @OnError
     public void onError(Session session, Throwable error) {
     System.out.println("发生错误");
     error.printStackTrace();
     }


     public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
     }

}