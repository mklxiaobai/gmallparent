package com.atguigu.locks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisIncrService {

    @Autowired
    StringRedisTemplate redisTemplate;

    public  synchronized void incr(){
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        String num = stringStringValueOperations.get("num");
        if(num!=null){
            Integer i = Integer.parseInt(num);
            i = i+1;
            stringStringValueOperations.set("num",i.toString());
        }else {
            redisTemplate.opsForValue().set("num","0");
        }
    }
}
