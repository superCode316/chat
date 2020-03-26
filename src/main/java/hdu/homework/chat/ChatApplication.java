package hdu.homework.chat;

import hdu.homework.chat.modules.message.socket.WebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@SpringBootApplication
@ServletComponentScan
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ChatApplication.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        //解决WebSocket不能注入的问题
        WebSocket.setApplicationContext(configurableApplicationContext);

    }

}
