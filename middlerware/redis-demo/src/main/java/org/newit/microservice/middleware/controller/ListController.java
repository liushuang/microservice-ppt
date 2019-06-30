package org.newit.microservice.middleware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/rightPush/{key}/{value}")
    public Object rightPush(@PathVariable("key") String key,@PathVariable("value")String value){
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @RequestMapping("/lrange/{key}")
    public Object set(@PathVariable("key") String key){
        return redisTemplate.opsForList().range(key,0,Long.MAX_VALUE);
    }
}
