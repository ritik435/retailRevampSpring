package com.example.retailRevamp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class BasicControlle {
    @GetMapping("/")
    public String hello(){
        return "Hello home";
    }
}
