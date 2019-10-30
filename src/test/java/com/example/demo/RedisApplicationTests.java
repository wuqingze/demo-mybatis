package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
class RedisApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void contextLoads() {
        //这里相当于redis对String类型的set操作
        redisTemplate.opsForValue().set("test","helloworld");
        //这里相当于redis对String类型的get操作
        String test = (String)redisTemplate.opsForValue().get("test");
        System.out.println(test);
    }
}
