package hdu.homework.chat.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * created by 钱曹宇@supercode on 2020/4/3
 */
@Data
@Component
@ConfigurationProperties(prefix = "rocketmq")
public class RocketMqConfig {
    private String server;
    private String topic;
    private String group;
}
