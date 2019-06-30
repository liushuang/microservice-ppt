package org.newit.microservice.kafka;

import org.newit.microservice.kafka.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application {

    @Autowired
    private ProducerService producerService;

    @RequestMapping("/sendData")
    @ResponseBody
    public String sendData(@RequestParam("data")String data){
        producerService.sendMessage(data);
        return "success";
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
