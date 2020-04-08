package hdu.homework.chat.modules.message.utils;

import com.google.gson.Gson;
import hdu.homework.chat.config.RocketMqConfig;
import hdu.homework.chat.entity.bean.database.Messages;
import hdu.homework.chat.modules.message.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.StringReader;

/**
 * created by 钱曹宇@supercode on 4/1/2020
 */
@Component
@Slf4j
public class Consumer implements CommandLineRunner {
    private RocketMqConfig config;
    private MessageService service;
    private Gson gson;
    public Consumer(RocketMqConfig config, MessageService service, Gson gson) {
        this.config = config;
        this.service = service;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(config.getGroup());

        consumer.setNamesrvAddr(config.getServer());

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe(config.getTopic(), "*");

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            log.info(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
            for (Message m : msgs) {
                Messages messages = gson.fromJson(new String(m.getBody()), Messages.class);
                service.sendMessage(messages.getSenderId(), messages.getToId(), messages.getContent());
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();

        log.info("Consumer Started.");
    }
}
