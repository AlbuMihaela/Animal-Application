package com.sda.project.mapper;

import com.sda.project.dto.PetDto;
import com.sda.project.model.Category;
import com.sda.project.model.Pet;
import org.springframework.stereotype.Service;

@Service
public class PetMapper {
    public Pet map(PetDto petDto) {
        Pet pet = new Pet();
        pet.setName(petDto.getName());
        pet.setCategory(Category.valueOf(petDto.getCategory()));
        pet.setDescription(petDto.getDescription());
        return pet;
    }
}
