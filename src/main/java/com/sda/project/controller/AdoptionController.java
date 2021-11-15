package com.sda.project.controller;

import com.sda.project.dto.AdoptionDto;
import com.sda.project.dto.PetDto;
import com.sda.project.mapper.PetMapper;
import com.sda.project.model.Pet;
import com.sda.project.model.User;
import com.sda.project.service.AdoptionService;
import com.sda.project.service.PetService;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AdoptionController {

    private final AdoptionService adoptionService;
    private final UserService userService;
    private final PetService petService;
    private final PetMapper petMapper;


    @Autowired
    public AdoptionController(AdoptionService adoptionService, UserService userService, PetService petService, PetMapper petMapper) {
        this.adoptionService = adoptionService;
        this.userService = userService;
        this.petService = petService;
        this.petMapper = petMapper;
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
        List<PetDto> pets = petService.getUserPetsAvailable();

        AdoptionDto adoptionDto = new AdoptionDto(loggedUser, null, null, null, null);

        model.addAttribute("adoptionDto", adoptionDto);
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("pets", pets);
        model.addAttribute("localDate", LocalDate.now());

        return "adoption/adoption-add";
    }

    @PostMapping("/adoptions/add")
    public String addAdoptForm( AdoptionDto adoptionDto) {
            adoptionService.save(adoptionDto);
            return "redirect:/home";

    }
    @GetMapping("/adoptions/about")
    public String getAboutAdoptionsPage() {
        return "adoption/adoptions-about";
    }

}
