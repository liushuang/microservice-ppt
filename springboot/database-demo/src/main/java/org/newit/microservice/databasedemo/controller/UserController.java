package org.newit.microservice.databasedemo.controller;

import org.newit.microservice.databasedemo.model.User;
import org.newit.microservice.databasedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/insert")
    @ResponseBody
    public boolean insertUser(@RequestParam("name")String name){
        User user = new User();
        user.setName(name);
        return userService.insertUser(user);
    }

    @RequestMapping("/user/get/{userId}")
    @ResponseBody
    public User user(@PathVariable("userId")Long userId){
        return userService.getUserById(userId);
    }
}
