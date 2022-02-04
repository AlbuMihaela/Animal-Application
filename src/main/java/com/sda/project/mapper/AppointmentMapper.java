package com.sda.project.mapper;

import com.sda.project.dto.AppointmentDto;
import com.sda.project.dto.AppointmentInfo;
import com.sda.project.dto.AppointmentInfo2;
import com.sda.project.model.Appointment;
import com.sda.project.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {
@Autowired
    private PetService petService;

    public Appointment map(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setUser(appointmentDto.getUser());
        appointment.setDate(LocalDateTime.parse(appointmentDto.getDate()));

        appointment.setPets(appointmentDto.getPetsNames().stream()
                .map(petName -> petService.findByName(petName)).collect(Collectors.toSet()));
        return appointment;
    }

    public AppointmentDto map(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setUser(appointment.getUser());
        appointmentDto.setPetsNames(appointment.getPets().stream()
                .map(pet -> pet.getName()).collect(Collectors.toSet()));
        appointmentDto.setDate(String.valueOf(appointment.getDate()));
        return appointmentDto;
    }

    public AppointmentInfo mapFromAppointmentToAppointmentInfo(Appointment appointment) {
        AppointmentInfo dto = new AppointmentInfo();
        dto.setDate(appointment.getDate());
        dto.setPets(appointment.getPets());
        dto.setUser(appointment.getUser());
        return dto;
    }

    public AppointmentInfo2 mapFromAppointmentToAppointmentInfo2(Appointment appointment) {
        AppointmentInfo2 dto = new AppointmentInfo2();
        dto.setDate(appointment.getDate());
        dto.setPets(appointment.getPets().stream().map(pet -> pet.getName()).collect(Collectors.toSet()));
        dto.setUser(appointment.getUser());
        return dto;
    }
}
