package com.sda.project.dto;

import com.sda.project.model.Pet;
import com.sda.project.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentDto {

    private User user;
    private Set<String> petsNames;
    private String date;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<String> getPets() {
        return petsNames;
    }

    public void setPetsNames(Set<String> petsNames) {
        this.petsNames = petsNames;
    }
}
