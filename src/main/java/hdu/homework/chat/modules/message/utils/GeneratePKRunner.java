package hdu.homework.chat.modules.message.utils;

import hdu.homework.chat.utils.RsaUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * created by 钱曹宇@supercode on 3/16/2020
 */
@Component
@Order(99)
public class GeneratePKRunner implements CommandLineRunner {
    private RedisTemplate<String, Object> redis;

    public GeneratePKRunner(RedisTemplate<String, Object> redis) {
        this.redis = redis;
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> keyPair = RsaUtils.genKeyPair();
        redis.opsForValue().set("server_public_key", RsaUtils.getPublicKey(keyPair));
        redis.opsForValue().set("server_private_key", RsaUtils.getPrivateKey(keyPair));
    }
}
