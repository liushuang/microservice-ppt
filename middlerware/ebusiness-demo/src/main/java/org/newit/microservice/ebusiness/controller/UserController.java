package org.newit.microservice.ebusiness.controller;

import org.newit.microservice.ebusiness.model.User;
import org.newit.microservice.ebusiness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    @ResponseBody
    public JSONObject register(@RequestBody User user){
        userService.register(user);
        JSONObject result = new JSONObject();
        result.put("success", true);
        return result;
    }
}
