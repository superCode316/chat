package hdu.homework.chat.modules.message.utils;

import hdu.homework.chat.config.RocketMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * created by 钱曹宇@supercode on 2020/4/3
 */
@Slf4j
@Component
public class Producer {
    private DefaultMQProducer defaultMQProducer;
    private RocketMqConfig config;
    public Producer(RocketMqConfig config) {
        this.config = config;
        defaultMQProducer = new DefaultMQProducer();
        defaultMQProducer.setProducerGroup(config.getGroup());
        defaultMQProducer.setVipChannelEnabled(false);
        defaultMQProducer.setNamesrvAddr(config.getServer());
        try {
            defaultMQProducer.start();
        } catch (MQClientException e) {
            log.error("无法启动mq: {}", e.getErrorMessage());
        }
    }
    public DefaultMQProducer getDefaultMQProducer() {
        return defaultMQProducer;
    }
    public void shutdown() {
        this.defaultMQProducer.shutdown();
    }
    public void sendMessage(String s) {
        try {
            Message message = new Message(config.getTopic(), s.getBytes(StandardCharsets.UTF_8));
            getDefaultMQProducer().send(message);
        } catch (Exception e) {
            log.error("无法发送消息: {}", e.getLocalizedMessage());
        }
    }
}
