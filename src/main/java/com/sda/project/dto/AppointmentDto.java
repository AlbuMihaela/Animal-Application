package com.sda.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDto {

    //TODO how do we deal in the dto class about the fields from entity relations

    private String date;
    private String appointmentStatus;
}
