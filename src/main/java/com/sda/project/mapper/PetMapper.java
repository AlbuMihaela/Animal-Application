package com.sda.project.mapper;

import com.sda.project.dto.PetDto;
import com.sda.project.dto.PetInfo;
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
        entity.setAdoption(dto.getAdoption());
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
        dto.setAdoption(entity.getAdoption());
        return dto;
    }

    // toEntity(PetInfo dto)
    public Pet mapFromPetInfoToPet(PetInfo petInfo) {
        Pet entity = new Pet();
        entity.setName(petInfo.getName());
        entity.setCategory(petInfo.getCategory());
        entity.setDescription(petInfo.getDescription());
        return entity;
    }

    // to PetInfo(Pet entity)
    public PetInfo mapFromPetToPetInfo(Pet entity) {
        PetInfo petInfo = new PetInfo();
        petInfo.setName(entity.getName());
        petInfo.setCategory(entity.getCategory());
        petInfo.setDescription(entity.getDescription());
        return petInfo;
    }

}
