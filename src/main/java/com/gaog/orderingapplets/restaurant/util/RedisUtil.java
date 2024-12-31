package com.gaog.orderingapplets.restaurant.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.gaog.orderingapplets.restaurant.util
 * @Date：2024/12/30 16:56
 * @Version： 1.0.0
 * @Description：
 * @Author： ZSJ
 */
@Component
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Set a value in Redis with an optional expiration time
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    // Get a value from Redis
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // Delete a key from Redis
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    // Check if a key exists in Redis
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    // Increment a value in Redis
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    // Set a value in Redis with a specific expiration time
    public void setWithExpiration(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    // Get the expiration time of a key
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }
}
