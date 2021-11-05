package com.sda.project.mapper;

import com.sda.project.dto.AdoptionDto;
import com.sda.project.model.Adoption;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AdoptionMapper {

    public Adoption map(AdoptionDto adoptionDto) {
        Adoption adoption = new Adoption();
        adoption.setAdoptionDate(LocalDate.parse(adoptionDto.getDate()));
        adoption.setSocialSecurityNumber(adoptionDto.getIdentityCard());
        adoption.setProofOfAddress(adoptionDto.getProofOfAddress());
        adoption.setProofOfFinancialSituation(adoptionDto.getProofOfFinancialSituation());
        adoption.setPet(adoptionDto.getPet());
        return adoption;
    }

    public AdoptionDto map(Adoption adoption) {
        AdoptionDto adoptionDto = new AdoptionDto();
        adoptionDto.setDate(String.valueOf(adoption.getAdoptionDate()));
        adoptionDto.setIdentityCard(adoption.getSocialSecurityNumber());
        adoptionDto.setProofOfAddress(adoption.getProofOfAddress());
        adoptionDto.setProofOfFinancialSituation(adoption.getProofOfFinancialSituation());
        adoptionDto.setPet(adoption.getPet());
        return adoptionDto;
    }
}
