package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {


    @Value("${welcome.message}")
    private String input;

    public WelcomeController(

    ) {


    }

    public WelcomeController(
             String input
    ) {
        this.input = input;

    }


    @GetMapping("/")
    public String sayHello() {
        return input;
    }

}