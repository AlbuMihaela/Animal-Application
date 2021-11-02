package com.sda.project.controller;

import com.sda.project.dto.PetDto;
import com.sda.project.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetController {

    public final PetService petService;

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


    @GetMapping("/pets")
    public String getPetsPage(Model model) {
        model.addAttribute("petsDto", petService.findAll());
        return "pet/pets";
    }

    @GetMapping("/pet-add")
    public String getAddForm(Model model) {
        model.addAttribute("petDto", new PetDto());
        return "pet/pet-add";
    }

    @PostMapping("/pets/add")
    public String addPetForm(@ModelAttribute("petDto") PetDto petDto) {
        petService.save(petDto);
        return "redirect:/pets";
    }

    //TODO how do we do update object

    /*
        GET    /pets/{id}
        POST   /pets/{id} 		(json body)
     */

    // show edit form
    @GetMapping("/pets/{id}/edit")
    public String getEditForm(Model model, @PathVariable Long id) {
        // need pet data from db
        PetDto petToUpdate = petService.findById(id);

        // add data to model
        model.addAttribute("petDto", petToUpdate);
        return "pet/pet-edit";
    }

    // update
    @PostMapping("/pets/{id}/edit")
    public String update(@PathVariable Long id,
                         @ModelAttribute PetDto petDto) {
        // update
        petService.update(petDto);

        // after update
        return "redirect:/pets";
    }
}
