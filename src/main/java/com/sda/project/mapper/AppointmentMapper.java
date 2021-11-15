package com.sda.project.mapper;

import com.sda.project.dto.AppointmentDto;
import com.sda.project.dto.AppointmentInfo;
import com.sda.project.dto.PetDto;
import com.sda.project.model.Appointment;
import com.sda.project.model.AppointmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {

    public Appointment map(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setUser(appointmentDto.getUser());
        appointment.setDate(LocalDate.parse(appointmentDto.getDate()));

        appointment.setPets(appointmentDto.getPets());
        return appointment;
    }

    public AppointmentDto map(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setUser(appointment.getUser());
        appointmentDto.setPets(appointment.getPets());
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
}
