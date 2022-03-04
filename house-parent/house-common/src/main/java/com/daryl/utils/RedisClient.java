package com.daryl.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by Daryl
 * on2021-09-17     09h:45m:22s
 */
@Component
public class RedisClient {
    public static final long TOKEN_EXPIRES_SECOND = 1800;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 向redis中设值
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 想redis 中设值，同时设置过期时间
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, Object value, long time) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取redis中的值
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        Object result = null;
        try {
            result = redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设置kye 的过期时间
     *
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time) {
        boolean result = false;
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据key 删除对应的value
     *
     * @param key
     * @return
     */
    public boolean remove(String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断某个key是否还存在
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        boolean result = true;
        if (redisTemplate.opsForValue().get(key) == null) {
            result = false;
        }
        return result;
    }

}
