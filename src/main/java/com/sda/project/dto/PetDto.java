package com.sda.project.dto;

import com.sda.project.model.Adoption;
import com.sda.project.model.Pet;
import com.sda.project.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Transient;

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
    private String photo;



    public Adoption getAdoption() {
        return adoption;
    }

    public void setAdoption(Adoption adoption) {
        this.adoption = adoption;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    @Transient
    public String getPhotoImagePath() {
        if (photo == null || id == null) return null;

        return "/pet-photos/" + id + "/" + photo;
    }
}
