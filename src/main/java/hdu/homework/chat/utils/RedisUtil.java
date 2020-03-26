package hdu.homework.chat.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * created by 钱曹宇@supercode on 3/26/2020
 */
@Component
public class RedisUtil {
    private RedisTemplate<String, Object> redis;

    public RedisUtil(RedisTemplate<String, Object> redis) {
        this.redis = redis;
    }

    public void set(String key, Object obj) {
        redis.opsForValue().set(key, obj);
    }

    public Object get(String key) {
        return redis.opsForValue().get(key);
    }

    public Long setGroupNumber(String key, Integer...users) {
        redis.opsForSet().add(key, users);
        return redis.opsForSet().size(key);
    }

    public Long removeGroupNumber(String key, Integer...users) {
        redis.opsForSet().remove(key, users);
        return redis.opsForSet().size(key);
    }

    public Long getGroupNumber(String key) {
        return redis.opsForSet().size(key);
    }

    public Set<Object> getGroupMember(String key) {
        return redis.opsForSet().members(key);
    }

}
