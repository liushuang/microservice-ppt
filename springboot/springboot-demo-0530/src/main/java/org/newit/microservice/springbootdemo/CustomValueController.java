package org.newit.microservice.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomValueController {

    @Value("newit.title")
    private String title;

    @Value("${profile.title.test}")
    private String profileTitle;

    @Autowired
    private Book mybook;

    @RequestMapping("/book")
    public Book book(){
        return mybook;
    }

    @RequestMapping("/customTitle")
    public String customTitle(){
        return title;
    }

    @RequestMapping("/profileTitle")
    public String profileTitle(){
        return profileTitle;
    }


}
