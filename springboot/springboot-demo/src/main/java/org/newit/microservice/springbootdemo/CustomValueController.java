package org.newit.microservice.springbootdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomValueController {

    @Value("newit.title")
    private String title;

    @Value("${profile.title.test}")
    private String profileTitle;

    @RequestMapping("customTitle")
    public String customTitle(){
        return title;
    }

    @RequestMapping("profileTitle")
    public String profileTitle(){
        return profileTitle;
    }
}
