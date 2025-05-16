package com.example.retailRevamp.Service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisServiceTest {

    private static final Log log = LogFactory.getLog(RedisServiceTest.class);
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testEmailService(){
        Object work=redisTemplate.opsForValue().get("work");
        System.out.println(work);
        redisTemplate.opsForValue().set("salary","none");
        redisTemplate.opsForValue().set("work","hardest");
    }
    @Test
    public void testRedis() {
        Object val=redisTemplate.opsForValue().get("work");
        log.info("val : "+val);
        redisTemplate.opsForValue().set("testKey", "connected!1");
        String value = redisTemplate.opsForValue().get("testKey").toString();
        System.out.println("✅ Redis Value: " + value);
    }
}
