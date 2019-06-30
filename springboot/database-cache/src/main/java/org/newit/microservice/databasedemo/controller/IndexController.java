package org.newit.microservice.databasedemo.controller;

import java.util.List;

import org.newit.microservice.databasedemo.model.User;
import org.newit.microservice.databasedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userDetail/{userId}")
    public String userDetail(@PathVariable("userId")long userId, Model model){
        User userById = userService.getUserById(userId);
        model.addAttribute("user", userById);
        return "userDetail";
    }

    @RequestMapping("/userList")
    public String userList(Model model){
        List<User> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        return "userList";
    }
}
