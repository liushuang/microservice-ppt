package org.newit.microservice.middleware;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@SpringBootApplication
public class Application {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init(){
        initRedisTemplate();
    }

    private void initRedisTemplate() {

        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
