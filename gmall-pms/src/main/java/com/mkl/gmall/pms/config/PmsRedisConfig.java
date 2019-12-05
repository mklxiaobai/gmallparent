package com.mkl.gmall.pms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @author 马锴梁
 * @version 1.0
 * @date 2019/12/2 10:03
 */
@Configuration
public class PmsRedisConfig {
    @Bean("redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //修改默认的序列化方式
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
