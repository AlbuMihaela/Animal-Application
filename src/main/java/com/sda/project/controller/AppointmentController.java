package com.sda.project.controller;

import com.sda.project.dto.AdoptionDto;
import com.sda.project.dto.AppointmentDto;
import com.sda.project.dto.AppointmentInfo;
import com.sda.project.dto.PetDto;
import com.sda.project.model.Pet;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
        User loggedUser = userService.findByEmail(email);
        List<PetDto> pets = petService.getUserPetsAvailable();
        AppointmentDto appointmentDto = new AppointmentDto(loggedUser, null, null);
        model.addAttribute("appointmentDto", appointmentDto);
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("pets", pets);
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "appointment/appointment-add";
    }

    //TODO de ce nu putem salva un appointment in baza de date? ne da eroare!
    @PostMapping("/appointments/add")
    public String addAppointmentForm(AppointmentDto appointmentDto) {
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
        List<AppointmentInfo> myAppointments = appointmentService.findAppointmentsByUser(user);
        model.addAttribute("myAppointmentsInfo", myAppointments);
        return "appointment/my-appointments";
    }
}
