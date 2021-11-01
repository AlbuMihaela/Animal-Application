package com.sda.project.mapper;

import com.sda.project.dto.AppointmentDto;
import com.sda.project.model.Appointment;
import com.sda.project.model.AppointmentStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentMapper {

    public Appointment map(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setDate(LocalDateTime.parse(appointmentDto.getDate()));
        appointment.setAppointmentStatus(AppointmentStatus.valueOf(appointmentDto.getAppointmentStatus()));
        return appointment;
    }

    public AppointmentDto map(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setDate(String.valueOf(appointment.getDate()));
        appointmentDto.setAppointmentStatus(String.valueOf(appointment.getAppointmentStatus()));
        return appointmentDto;
    }

}
