package com.sda.project.mapper;

import com.sda.project.dto.AppointmentDto;
import com.sda.project.dto.AppointmentInfo;
import com.sda.project.dto.PetDto;
import com.sda.project.model.Appointment;
import com.sda.project.model.AppointmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class AppointmentMapper {
    @Autowired
    private  PetMapper petMapper;

    public Appointment map(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setDate(LocalDateTime.parse(appointmentDto.getDate()));
        appointment.setAppointmentStatus(AppointmentStatus.valueOf(appointmentDto.getAppointmentStatus()));
        return appointment;
    }

    public AppointmentDto map(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setUser(appointment.getUser());
        appointmentDto.setPetsDto(appointment.getPets().stream().map(pet-> petMapper.map(pet)).collect(Collectors.toSet()));
        appointmentDto.setDate(String.valueOf(appointment.getDate()));
        appointmentDto.setAppointmentStatus(String.valueOf(appointment.getAppointmentStatus()));
        return appointmentDto;
    }

    public AppointmentInfo mapFromAppointmentToAppointmentInfo(Appointment appointment) {
        AppointmentInfo dto = new AppointmentInfo();
        dto.setDate(appointment.getDate());
        dto.setAppointmentStatus(appointment.getAppointmentStatus());
        dto.setPets(appointment.getPets());
        return dto;
    }
}
