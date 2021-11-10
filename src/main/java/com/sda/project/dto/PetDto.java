package com.sda.project.dto;

import com.sda.project.model.Adoption;
import com.sda.project.model.Pet;
import com.sda.project.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetDto {

    // needed to press Update pet
    private Long id;
    private String name;
    private String category;
    private String description;
    private boolean isAvailable;
    private Adoption adoption;



    public Adoption getAdoption() {
        return adoption;
    }

    public void setAdoption(Adoption adoption) {
        this.adoption = adoption;
    }



}
