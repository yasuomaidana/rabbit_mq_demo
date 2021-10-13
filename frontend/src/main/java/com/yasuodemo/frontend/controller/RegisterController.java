package com.yasuodemo.frontend.controller;

import com.yasuodemo.frontend.dto.DogDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @GetMapping
    public String register(){
        return "register";
    }
    @PostMapping
    public String newDogRegister(Model model, DogDto dog){
        System.out.println(dog.getName());
        return "show";
    }
}
