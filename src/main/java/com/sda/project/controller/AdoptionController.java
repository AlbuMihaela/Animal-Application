package com.sda.project.controller;

import com.sda.project.dto.AdoptionDto;
import com.sda.project.model.User;
import com.sda.project.service.AdoptionService;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdoptionController {

    private final AdoptionService adoptionService;
    private final UserService userService;


    @Autowired
    public AdoptionController(AdoptionService adoptionService, UserService userService) {
        this.adoptionService = adoptionService;
        this.userService = userService;
    }

    @GetMapping("/adoptions")
    public String getPetsPage(Model model) {
        model.addAttribute("adoptions", adoptionService.findAll());
        return "adoption/adoptions";
    }

    @GetMapping("/adoptions/add")
    public String getAdoptionsPage(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        // TODO: map user to user info
        User loggedUser = userService.findByEmail(email);

        AdoptionDto adoptionDto=new AdoptionDto(loggedUser.getId(), null,null,null,null);
        model.addAttribute("adoptionDto", new AdoptionDto());
        model.addAttribute("loggedUser",loggedUser);
        return "adoption/adoption-add";
    }

    @PostMapping("/adoptions/add")
    public String addAdoptForm(@ModelAttribute("adoptionDto") AdoptionDto adoptionDto) {
        adoptionService.save(adoptionDto);
        return "redirect:/home";
    }

    @GetMapping("/adoptions/about")
    public String getAboutAdoptionsPage() {
        return "adoption/adoptions-about";
    }

}
