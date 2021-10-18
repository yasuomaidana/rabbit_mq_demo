package com.example.frontend.controller;

import com.example.frontend.service.DogService;
import dto.DogDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/")
@AllArgsConstructor
public class DogController {

    private DogService dogService;

    @GetMapping("/show")
    public String show(Model model){
        model.addAttribute("dogs",dogService.getDogs());
        return "show";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String newDogRegister(Model model,DogDto dog){
        dogService.saveDog(dog);
        model.addAttribute("dogs",dogService.getDogs());
        return "show";
    }


}
