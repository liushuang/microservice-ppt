package org.newit.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application {

    @RequestMapping("/hell")
    @ResponseBody
    public String hello(){
        return "Hello world";
    }
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
