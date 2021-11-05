package com.sda.project.service;

import com.sda.project.dto.AppointmentDto;
import com.sda.project.dto.AppointmentInfo;
import com.sda.project.mapper.AppointmentMapper;
import com.sda.project.model.Appointment;
import com.sda.project.model.User;
import com.sda.project.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }


    public List<AppointmentDto> findAll() {
        return appointmentRepository.findAll().stream()
                .map(appointment -> appointmentMapper.map(appointment))
                .collect(Collectors.toList());
    }

    public Appointment save(AppointmentDto appointmentDto) {
        return appointmentRepository.save(appointmentMapper.map(appointmentDto));
    }

    public Set<AppointmentInfo> findAppointmentsByUser(User user) {
        return appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getUser().equals(user))
                .map(appointment -> appointmentMapper.mapFromAppointmentToAppointmentInfo(appointment))
                .collect(Collectors.toSet());

    }
}
