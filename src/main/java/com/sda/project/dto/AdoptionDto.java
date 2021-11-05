package com.sda.project.dto;

import com.sda.project.model.Pet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdoptionDto {

    private String date;
    private String proofOfAddress;
    private String proofOfFinancialSituation;
    private String identityCard;
    private Pet pet;
}
