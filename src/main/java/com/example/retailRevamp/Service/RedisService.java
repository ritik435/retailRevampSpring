package com.example.retailRevamp.Service;

import com.example.retailRevamp.Model.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@CommonsLog
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String key, Object value,Long ttl){

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json=mapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key,json,ttl, TimeUnit.HOURS);
            log.info("setting the key is successfull");
        }catch (Exception e) {
            log.info("error in setting for key : " + e.getMessage());
            e.printStackTrace();
        }
    }
    public <T>T get(String key,Class<T> entityClass) {
        try {
            Object object = redisTemplate.opsForValue().get(key);
            System.out.println(object);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(object.toString(), entityClass);
        }catch (Exception e) {
            log.info("error in getting for key : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
