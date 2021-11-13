package com.sda.project.mapper;

import com.sda.project.dto.AdoptionDto;
import com.sda.project.model.Adoption;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Component
public class AdoptionMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private PetMapper petMapper;

    public Adoption map(AdoptionDto adoptionDto) {
        Adoption adoption = new Adoption();

//        adoption.setAdoptionDate(LocalDate.parse(adoptionDto.getDate()));
        adoption.setSocialSecurityNumber(adoptionDto.getSocialSecurityNumber());
        adoption.setAddress(adoptionDto.getAddress());
        adoption.setPet(adoptionDto.getPet());
        adoption.setUser(adoptionDto.getUser());
        return adoption;
    }

    public AdoptionDto map(Adoption adoption) {
        AdoptionDto adoptionDto = new AdoptionDto();
        adoptionDto.setDate(String.valueOf(adoption.getAdoptionDate()));
        adoptionDto.setSocialSecurityNumber(adoption.getSocialSecurityNumber());
        adoptionDto.setAddress(adoption.getAddress());
        adoptionDto.setPet(adoption.getPet());
        adoptionDto.setUser(adoption.getUser());
        return adoptionDto;
    }


}
