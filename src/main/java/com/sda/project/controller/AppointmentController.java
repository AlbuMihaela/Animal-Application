package com.sda.project.controller;

import com.sda.project.dto.AppointmentDto;
import com.sda.project.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments")
    public String getAppointmentsPage(Model model) {
        model.addAttribute("appointmentsDto", appointmentService.findAll());
        return "appointment/appointments";
    }

    @GetMapping("/appointments/add")
    public String getAppointmentForm(Model model) {
        model.addAttribute("appointmentDto", new AppointmentDto());
        return "appointment/appointment-add";
    }

    @PostMapping("/appointments/add")
    public String addPetForm(@ModelAttribute("appointmentDto") AppointmentDto appointmentDto) {
        appointmentService.save(appointmentDto);
        return "redirect:/home";
    }
}
