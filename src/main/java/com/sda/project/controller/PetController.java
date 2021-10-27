package com.sda.project.controller;

import com.sda.project.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PetController {

    @Autowired
    public PetService petService;

    /*
        POST   /pets 			(json body)
        GET    /pets            @GetMapping("/pets")
        GET    /pets/{id}
        POST   /pets/{id} 		(json body)
        DELETE /pets/{id}
     */

    // show pet list
    // TODO: step 1
    @GetMapping("/pets")
    public String getPetPage(Model model) {
        model.addAttribute("pets", petService.findAll());
        return "pet/pets";
    }
}
