package com.sda.project.dto;

import com.sda.project.model.Pet;
import com.sda.project.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AppointmentDto {

    private User user;
    private Set<Pet> pets;
    private String date;
    private String appointmentStatus;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
