package com.yasuodemo.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/show")
public class ShowController {
    @GetMapping
    public String show(){
        return "show";
    }
}
