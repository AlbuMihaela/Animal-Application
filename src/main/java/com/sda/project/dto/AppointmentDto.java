package com.sda.project.dto;

import com.sda.project.model.Pet;
import com.sda.project.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class AppointmentDto {

    private User user;

    private Set<PetDto> petsDto;

    private String date;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<PetDto> getPets() {
        return petsDto;
    }

    public void setPets(Set<PetDto> pets) {
        this.petsDto = pets;
    }
}
