package com.sda.project.controller;

import com.sda.project.dto.AppointmentDto;
import com.sda.project.dto.AppointmentInfo;
import com.sda.project.model.User;
import com.sda.project.repository.PetRepository;
import com.sda.project.service.AppointmentService;
import com.sda.project.service.PetService;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final PetService petService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, UserService userService, PetService petService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.petService = petService;
    }

    @GetMapping("/appointments")
    public String getAppointmentsPage(Model model) {
        model.addAttribute("appointmentsDto", appointmentService.findAll());
        return "appointment/appointments";
    }

    @GetMapping("/appointments/add")
    public String getAppointmentForm(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        // TODO: map user to user info
        User loggedUser = userService.findByEmail(email);
//        Long loggedUserId = loggedUser.getId();
        model.addAttribute("appointmentDto", new AppointmentDto());
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("pets", petService.findAll());
        return "appointment/appointment-add";
    }
//TODO de ce nu putem salva un appointment in baza de date? ne da eroare!
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
