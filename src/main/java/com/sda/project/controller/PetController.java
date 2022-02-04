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
        return "redirect:/pets";
    }

    @GetMapping("/pets/{id}/edit")
    public String getEditForm(Model model, @PathVariable Long id) {
        PetDto petToUpdate = petService.findById(id);
        model.addAttribute("petDto", petToUpdate);
        return "pet/pet-edit";
    }

    @PostMapping("/pets/{id}/edit")
    public String update(@ModelAttribute PetDto petDto,
                         @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        petDto.setPhoto(fileName);
        Pet savedPetDto = petService.update2(petDto);
        return "redirect:/pets";
    }

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

}
