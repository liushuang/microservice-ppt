package org.newit.microservice.middleware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/key")
public class KeyController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/get/{key}")
    public Object get(@PathVariable("key") String key){
        return redisTemplate.opsForValue().get(key);
    }

    @RequestMapping("/set/{key}/{value}")
    public String set(@PathVariable("key") String key, @PathVariable("value")String value){
        redisTemplate.opsForValue().set(key,value);
        return "success";
    }
}
