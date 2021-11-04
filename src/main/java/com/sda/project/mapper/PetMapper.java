package com.sda.project.mapper;

import com.sda.project.dto.PetDto;
import com.sda.project.model.Category;
import com.sda.project.model.Pet;
import org.springframework.stereotype.Service;

@Service
//TODO ?????????????? @Servicec sau @Component
public class PetMapper {

    // toEntity(PetDto dto)
    public Pet map(PetDto dto) {
        Pet entity = new Pet();
        entity.setName(dto.getName());
        entity.setCategory(Category.valueOf(dto.getCategory()));
        entity.setDescription(dto.getDescription());
        return entity;
    }

    // update(PetDto dto, Pet entity)
    public Pet update(Pet petToUpdate, PetDto data) {
        petToUpdate.setName(data.getName());
        petToUpdate.setCategory(Category.valueOf(data.getCategory()));
        petToUpdate.setDescription(data.getDescription());
        return petToUpdate;
    }

    // toDto(Pet entity)
    public PetDto map(Pet entity) {
        PetDto dto = new PetDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCategory(String.valueOf(entity.getCategory()));
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
