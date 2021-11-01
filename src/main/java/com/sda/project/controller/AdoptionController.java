package com.sda.project.controller;

import com.sda.project.dto.AdoptionDto;
import com.sda.project.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdoptionController {

    private final AdoptionService adoptionService;

    @Autowired
    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }


    @GetMapping("/adoptions")
    public String getPetsPage(Model model) {
        model.addAttribute("adoptions", adoptionService.findAll());
        return "adoption/adoptions";
    }

    @GetMapping("/adoption-add")
    public String getAdoptionsPage(Model model) {
        model.addAttribute("adoptionDto", new AdoptionDto());
        return "adoption/adoption-add";
    }

    @PostMapping("/adoptions/add")
    public String addAdoptForm(Model model,@ModelAttribute("adoptionDto") AdoptionDto adoptionDto) {
        adoptionService.save(adoptionDto);
        return "redirect:/home";
    }


}
