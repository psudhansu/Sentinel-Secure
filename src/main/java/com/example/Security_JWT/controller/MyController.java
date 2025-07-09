package com.example.Security_JWT.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/welcome")
    private String Welcome(){
        return "Welcome to Security";
    }




}
