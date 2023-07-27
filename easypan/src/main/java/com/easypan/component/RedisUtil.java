package com.easypan.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Configuration
public class RedisUtil<v> {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Resource
    private RedisTemplate <String, v> redisTemplate;

    @Bean
    public RedisTemplate redisTemplateInit() {
        //设置序列化key的实例化对象
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化value的实例化对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
    /*
    删除缓存
     */
    public void delete(String... key) {
        if(key != null && key.length > 0 ) {
            if(key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }
    public v get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, v value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            logger.error("设置key-value failed", key, value);
            return false;
        }
    }
    /**
     *
     */
    public boolean setText(String key, v value, long time) {
        try {
            if(time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            logger.error("fail to reset key & value");
            return false;
        }
    }
}