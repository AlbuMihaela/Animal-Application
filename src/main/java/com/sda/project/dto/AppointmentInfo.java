package com.sda.project.dto;

import com.sda.project.model.AppointmentStatus;
import com.sda.project.model.Pet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class AppointmentInfo {

    private LocalDateTime date;
    private AppointmentStatus appointmentStatus;
    private Set<Pet> pets;

}
