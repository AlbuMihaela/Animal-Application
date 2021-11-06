package com.sda.project.controller;

import com.sda.project.dto.PetDto;
import com.sda.project.model.Category;
import com.sda.project.model.User;
import com.sda.project.service.PetService;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetController {

    private final PetService petService;
    private final UserService userService;


    @Autowired
    public PetController(PetService petService, UserService userService) {
        this.petService = petService;
        this.userService = userService;
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

    @GetMapping("/pets/add")
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


    // TODO IS NOT WORKING. PLS CHECK!
    @GetMapping("/pets/{id}/delete")
    public String delete(Model model, @PathVariable Long id) {
        petService.deleteById(id);
        return  "redirect:/pets";
    }


    @GetMapping("/my-pets")
    public String getMyPetsPage(Model model) {
        User user = userService.findLoggedUser();
        model.addAttribute("myPets", petService.findPetsByUser(user));
        return "adoption/my-pets";
    }

    @GetMapping("/dogs")
    public String getDogsPage(Model model) {
        model.addAttribute("dogs", petService.findByCategory(Category.DOG));
        return "pet/dogs";
    }

    @GetMapping("/cats")
    public String getCatsPage(Model model) {
        model.addAttribute("cats", petService.findByCategory(Category.CAT));
        return "pet/cats";
    }

    @GetMapping("/rabbits")
    public String getRabbitsPage(Model model) {
        model.addAttribute("rabbits", petService.findByCategory(Category.RABBIT));
        return "pet/rabbits";
    }

    @GetMapping("/guinea_pigs")
    public String getGuineaPage(Model model) {
        model.addAttribute("guinea_pigs", petService.findByCategory(Category.GUINEA_PIG));
        return "pet/guinea_pigs";
    }

    @GetMapping("/birds")
    public String getBirdsPage(Model model) {
        model.addAttribute("birds", petService.findByCategory(Category.BIRD));
        return "pet/birds";
    }


}
