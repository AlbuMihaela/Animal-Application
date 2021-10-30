package com.sda.project.mapper;

import com.sda.project.dto.AdoptionDto;
import com.sda.project.model.Adoption;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdoptionMapper {

    public Adoption map(AdoptionDto adoptionDto) {
        Adoption adoption = new Adoption();
        adoption.setDate(LocalDateTime.parse(adoptionDto.getDate()));
        adoption.setIdentityCard(adoptionDto.getIdentityCard());
        adoption.setProofOfAddress(adoptionDto.getProofOfAddress());
        adoption.setProofOfFinancialSituation(adoptionDto.getProofOfFinancialSituation());
        return adoption;
    }

}
