package com.sda.project.controller;

import com.sda.project.dto.AdoptionDto;
import com.sda.project.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class AdoptionController {

    private final AdoptionService adoptionService;

    @Autowired
    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    // FIXME: implement later
    @GetMapping("/adoptions")
    public String getAdoptionsPage(Model model) {
        model.addAttribute("adoptionDto", new AdoptionDto());
        return "adoption/adoptions";
    }

    // FIXME: implement later
    @PostMapping("/adoptions/add")
    public String addAdoptForm(Model model,@ModelAttribute("adoptionDto") AdoptionDto adoptionDto) {
        adoptionService.save(adoptionDto);
        model.addAttribute("adoptionDto", adoptionDto);
        return "redirect:/user";
    }
}
