package com.sda.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController {

    @GetMapping(value="/registration")
    public String getRegistrationPage(){
        return "registration";
    }
}
