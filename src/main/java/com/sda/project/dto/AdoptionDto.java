package com.sda.project.dto;

import com.sda.project.model.Pet;
import com.sda.project.model.User;
import com.sda.project.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdoptionDto {


    private Long userId;
    private String date;
    private String address;
    private String socialSecurityNumber;
    private PetDto petDto;



}
