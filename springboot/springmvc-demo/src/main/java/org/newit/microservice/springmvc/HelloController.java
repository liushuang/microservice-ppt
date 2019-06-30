package org.newit.microservice.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/world")
    @ResponseBody
    public String world(){
        return "hello world";
    }

    @RequestMapping("/index")
    public String hello(){
        return "index";
    }

    @RequestMapping("/currentTime")
    @ResponseBody
    public MyTime currentTime(){
        MyTime myTime = new MyTime();
        myTime.setCurrentTime(System.currentTimeMillis());
        return myTime;
    }
}
