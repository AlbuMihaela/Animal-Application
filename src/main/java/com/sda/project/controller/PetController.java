package com.sda.project.controller;

import com.sda.project.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PetController {

    public PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    /*
        POST   /pets 			(json body)
        GET    /pets            @GetMapping("/pets")
        GET    /pets/{id}
        POST   /pets/{id} 		(json body)
        DELETE /pets/{id}
     */

    // TODO: implement showAddForm() -> return pet-add.html

    // TODO: implement add() -> redirect to pets

    @GetMapping("/pets")
    public String getPetsPage(Model model) {
        model.addAttribute("pets", petService.findAll());
        return "pet/pets";
    }

}
