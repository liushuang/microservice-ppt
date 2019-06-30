package org.newit.microservice.middleware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HashController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/hash/hashPut")
    @ResponseBody
    public String hashPut(@RequestParam("hashKey") String hashKey,
                          @RequestParam("hashValue") String hashValue){
        redisTemplate.opsForHash().put("config", hashKey, hashValue);
        return "success";
    }

    @RequestMapping("/hash/hashGet")
    @ResponseBody
    public Object hashGet(@RequestParam("hashKey") String hashKey){
        return redisTemplate.opsForHash().get("config", hashKey);
    }
}
