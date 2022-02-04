package com.sda.project.mapper;

import com.sda.project.dto.AdoptionDto;
import com.sda.project.model.Adoption;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AdoptionMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private PetMapper petMapper;

    public Adoption toEntity(AdoptionDto dto) {
        Adoption entity = new Adoption();
        entity.setAdoptionDate(LocalDate.parse(dto.getDate()));
        entity.setSocialSecurityNumber(dto.getSocialSecurityNumber());
        entity.setAddress(dto.getAddress());
        return entity;
    }

    public AdoptionDto toDto(Adoption entity) {
        AdoptionDto dto = new AdoptionDto();
        dto.setDate(String.valueOf(entity.getAdoptionDate()));
        dto.setSocialSecurityNumber(entity.getSocialSecurityNumber());
        dto.setAddress(entity.getAddress());
        dto.setPet(entity.getPet());
        dto.setUser(entity.getUser());
        return dto;
    }

}
