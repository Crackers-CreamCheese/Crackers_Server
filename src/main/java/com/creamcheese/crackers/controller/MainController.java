package com.creamcheese.crackers.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/main")
public class MainController {
    @GetMapping("/hello")
    public String getHello(){
        return "Hello!";
    }
}
