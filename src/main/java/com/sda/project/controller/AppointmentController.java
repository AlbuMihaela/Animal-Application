package com.sda.project.controller;

import com.sda.project.dto.AppointmentDto;
import com.sda.project.dto.AppointmentInfo;
import com.sda.project.model.User;
import com.sda.project.service.AppointmentService;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;


@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
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

    @GetMapping("/appointments/about")
    public String getAppointmentsAboutPage() {
        return "appointment/appointments-about";
    }

    @GetMapping("/my-appointments")
    public String getMyAppointmentsPage(Model model) {
        User user = userService.findLoggedUser();
        Set<AppointmentInfo> appointments = appointmentService.findAppointmentsByUser(user);
        model.addAttribute("appointmentsInfo", appointments);
        return "appointment/my-appointments";
    }
}
