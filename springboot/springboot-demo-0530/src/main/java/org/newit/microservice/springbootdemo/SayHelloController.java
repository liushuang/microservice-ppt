package org.newit.microservice.springbootdemo;

import org.newit.springboot.sayhello.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("sayHello")
    public String sayHello(){
        return helloService.sayHello();
    }
}
