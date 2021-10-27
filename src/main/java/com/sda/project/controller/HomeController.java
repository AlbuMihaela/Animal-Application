package com.sda.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/index"})
    public String getLandingPage() {
        return "index";
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }
}
