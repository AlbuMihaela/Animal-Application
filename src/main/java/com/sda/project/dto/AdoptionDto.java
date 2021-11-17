package com.sda.project.dto;

import com.sda.project.model.Pet;
import com.sda.project.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdoptionDto {

    private User user;
    private String date;
    private String address;
    private String socialSecurityNumber;
    private Pet pet;

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
