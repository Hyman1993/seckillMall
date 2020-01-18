package com.hyman.seckillMall.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.cdi.RedisKeyValueTemplateBean;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis的通用配置
 * @Author: penghuang
 * @Date: 2020/1/17 22:31
 * @Version 1.0
 */
@Configuration
public class CommonConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

     @Bean
     public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // TODO:指定key、value的序列化策略
         redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
     }

     @Bean
     public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
     }
}
