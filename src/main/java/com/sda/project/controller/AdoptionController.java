package com.sda.project.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class AdoptionController {

    // FIXME: implement later
    @GetMapping("/adoptions")
    public String getAdoptPage() {
        return "user/adoptions";
    }

    // FIXME: implement later
    @PostMapping("/adoptions")
    public String postAdoptPage() {
        return "user/adoptions";
    }
}
