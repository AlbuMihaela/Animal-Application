package com.sda.project.controller;

import com.sda.project.config.FileUploadUtil;
import com.sda.project.dto.PetDto;
import com.sda.project.dto.PetInfo;
import com.sda.project.model.Category;
import com.sda.project.model.Pet;
import com.sda.project.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.util.List;

@Controller
public class PetController {

    private final PetService petService;

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

    @GetMapping("/pets_user")
    public String getPetsPageForUser(Model model) {
        model.addAttribute("petsDto", petService.findAll());
        return "pet/pets_user";
    }


    @GetMapping("/pets/add")
    public String getAddForm(Model model) {
        model.addAttribute("petDto", new PetDto());
        return "pet/pet-add";
    }

    @PostMapping("/pets/add")
    public String addPetForm(PetDto petDto,
                             @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        petDto.setPhoto(fileName);
        PetDto savedPetDto = petService.save(petDto);


        String uploadDir = "pet-photos/" + savedPetDto.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

//        petService.save(petDto);
        return "redirect:/pets";
    }

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
                         @ModelAttribute PetDto petDto,
    @RequestParam ("image") MultipartFile multipartFile) throws IOException  {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        petDto.setPhoto(fileName);
        Pet savedPetDto = petService.update2(petDto);

        String uploadDir = "pet-photos/" + savedPetDto.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);


        // update
//        petService.update(petDto);

        // after update
        return "redirect:/pets";
    }

    // TODO IS NOT WORKING. PLS CHECK!
    @GetMapping("/pets/{id}/delete")
    public String delete(@PathVariable Long id) {
        petService.deleteById(id);
        return "redirect:/pets";
    }

    @GetMapping("/my-pets")
    public String getMyPetsPage(Model model) {
        model.addAttribute("myPets", petService.getUserPets());
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

    @GetMapping("/guinea-pigs")
    public String getGuineaPage(Model model) {
        model.addAttribute("guinea-pigs", petService.findByCategory(Category.GUINEA_PIG));
        return "pet/guinea-pigs";
    }

    @GetMapping("/birds")
    public String getBirdsPage(Model model) {
        model.addAttribute("birds", petService.findByCategory(Category.BIRD));
        return "pet/birds";
    }
}
