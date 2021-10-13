package com.yasuodemo.frontend.controller;

import com.yasuodemo.frontend.config.MQConfig;
import com.yasuodemo.frontend.dto.DogDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private RabbitTemplate template;

    @GetMapping
    public String register(){
        return "register";
    }
    @PostMapping
    public String newDogRegister(Model model, DogDto dog){
        template.convertAndSend(MQConfig.EXCHANGE,MQConfig.ROUTING_KEY,dog);
        return "show";
    }
}
