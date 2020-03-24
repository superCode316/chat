//package hdu.homework.chat.config;
//
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * created by 钱曹宇@supercode on 3/19/2020
// */
//@Configuration
//@ConfigurationProperties(prefix = "wss.server")
//public class SocketConfig {
//    private String host;
//    private Integer post;
//    @Value("${wss.origin.host}")
//    private String originHost;
//
//    @Bean
//    public SocketIOServer socketIOServer() {
//        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
//        configuration.setHostname(host);
//        configuration.setPort(post);
//        configuration.setOrigin(originHost);
//        return new SocketIOServer(configuration);
//    }
//
//    @Bean
//    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer s) {
//        return new SpringAnnotationScanner(s);
//    }
//}
