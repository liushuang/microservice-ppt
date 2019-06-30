package org.newit.microservice.springbootthymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index(Model model) {
        User user = new User();
        user.setName("test user name");
        user.setId(123L);
        model.addAttribute("user", user);
        return "index";
    }
}
