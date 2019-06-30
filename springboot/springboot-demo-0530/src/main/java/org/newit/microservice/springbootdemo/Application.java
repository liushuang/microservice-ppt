package org.newit.microservice.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello world";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/currentTime")
    @ResponseBody
    public CurrentTime currentTime(){
        CurrentTime currentTime = new CurrentTime();
        currentTime.setTime(System.currentTimeMillis());
        return currentTime;
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
