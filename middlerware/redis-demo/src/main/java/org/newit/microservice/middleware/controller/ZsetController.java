package org.newit.microservice.middleware.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/zset")
public class ZsetController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/init")
    @ResponseBody
    public String init(){
        IntStream.range(1,11).forEach((i) ->redisTemplate.opsForZSet().add("userList",String.valueOf(i),i));
        return "success";
    }
    @RequestMapping("/addScore")
    @ResponseBody
    public String addUser(@RequestParam("userId") int userId, @RequestParam("score") int score){
        redisTemplate.opsForZSet().incrementScore("userList", String.valueOf(userId), score);
        return "success";
    }

    @RequestMapping("/getUserList")
    @ResponseBody
    public Object getUserList(){
        List<String> result = new ArrayList<>();
        Set<TypedTuple<String>> rangeWithScore =
                redisTemplate.opsForZSet().reverseRangeWithScores("userList", 0, -1);
        Iterator<TypedTuple<String>> iterator = rangeWithScore.iterator();
        while(iterator.hasNext()){
            TypedTuple<String> next = iterator.next();
            result.add(next.getValue() + "|" + next.getScore());
        }
        return result;
    }
}
