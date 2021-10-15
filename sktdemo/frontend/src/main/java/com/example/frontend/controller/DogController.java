package com.example.frontend.controller;

import com.example.frontend.config.WebConfig;
import dto.DogDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/")
@AllArgsConstructor
public class DogController {

    private RabbitTemplate template;

    @GetMapping("/show")
    public String show(){
        return "show";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping("/register")
    public String newDogRegister(Model model, DogDto dog){
        template.convertAndSend(WebConfig.EXCHANGE,WebConfig.ROUTING_KEY,dog);
        return "show";
    }
}
