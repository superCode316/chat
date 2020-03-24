//package hdu.homework.chat.modules.message.socket;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * created by 钱曹宇@supercode on 3/15/2020
// */
//@ServerEndpoint(value = "/websocket/{ticket}")
//@Slf4j
//@RestController
//public class WebSocket {
//    private String username;
//    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
//    private static AtomicInteger onlineCount = new AtomicInteger(0);
//    private RedisTemplate<String, String> redis;
//    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
//    public static ConcurrentHashMap<String, WebSocket> webSocketSet = new ConcurrentHashMap<>();
//
//    @Autowired
//    public WebSocket(RedisTemplate<String, String> redis) {
//        this.redis = redis;
//    }
//
//    public static boolean send(String username, String msg) {
//        if (!webSocketSet.containsKey(username))
//            return false;
//        try {
//            webSocketSet.get(username).sendMessage(msg);
//            return true;
//        } catch (IOException e) {
//            log.error(e.getLocalizedMessage());
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    //与某个客户端的连接会话，需要通过它来给客户端发送数据
//    private Session session;
//
//    /**
//     * 连接建立成功调用的方法*/
//    @OnOpen
//    public void onOpen(Session session, @PathParam("ticket") String ticket) {
//        this.session = session;
//        String username = redis.opsForValue().get(ticket);
//        if (username == null) {
//            throw new RuntimeException();
//        }
//        this.username = username;
//        webSocketSet.put(username, this);     //加入set中
//        addOnlineCount();           //在线数加1
//        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
//    }
//
//    /**
//     * 连接关闭调用的方法
//     */
//    @OnClose
//    public void onClose() {
//        webSocketSet.remove(this.username);  //从set中删除
//        subOnlineCount();           //在线数减1
//        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
//    }
//
//    /**
//     * 收到客户端消息后调用的方法
//     *
//     * @param message 客户端发送过来的消息*/
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        System.out.println("来自客户端的消息:" + message);
//
//    }
//
//    /**
//     * 发生错误时调用
//     * */
//     @OnError
//     public void onError(Session session, Throwable error) {
//     System.out.println("发生错误");
//     error.printStackTrace();
//     }
//
//
//     public void sendMessage(String message) throws IOException {
//        this.session.getBasicRemote().sendText(message);
//     //this.session.getAsyncRemote().sendText(message);
//     }
//
//    public static synchronized int getOnlineCount() {
//        return onlineCount.get();
//    }
//
//    public static synchronized void addOnlineCount() {
//        WebSocket.onlineCount.addAndGet(1);
//    }
//
//    public static synchronized void subOnlineCount() {
//        WebSocket.onlineCount.getAndAdd(-1);
//    }
//}