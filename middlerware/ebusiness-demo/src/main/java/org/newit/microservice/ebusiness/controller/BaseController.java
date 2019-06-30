package org.newit.microservice.ebusiness.controller;

import org.newit.microservice.ebusiness.model.User;
import org.newit.microservice.ebusiness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    @Autowired
    private UserService userService;

    public User getLoginUser(){
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserByName(userDetails.getUsername());
    }
}
