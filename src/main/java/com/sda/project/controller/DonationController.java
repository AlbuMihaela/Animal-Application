package com.sda.project.controller;

import com.sda.project.dto.DonationAdd;
import com.sda.project.model.User;
import com.sda.project.service.DonationService;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DonationController {

    private final DonationService donationService;
    private final UserService userService;

    @Autowired
    public DonationController(DonationService donationService, UserService userService) {
        this.donationService = donationService;
        this.userService = userService;
    }

    @GetMapping("/donations")
    public String getDonationsPage(Model model) {
        model.addAttribute("donations", donationService.findAll());
        return "donation/donations";
    }

    @GetMapping("/donations/add")
    public String getDonationsForm(Model model) {
        // TODO move to service
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        // TODO: map user to user info
        User loggedUser = userService.findByEmail(email);
        Long loggedUserId = loggedUser.getId();

        DonationAdd donation = new DonationAdd(loggedUserId, null, null);
        model.addAttribute("donation", donation);
        model.addAttribute("loggedUser", loggedUser);
        return "donation/donation-add";
    }

    //TODO how can we put a placeholder in a dropdown list without saving it in database

    //TODO why can't we add donation to db(submit doesn't work)
    @PostMapping("donations/add")
    public String addDonationForm(@ModelAttribute("donationDto") DonationAdd donationAdd) {
        donationService.save(donationAdd);
        return "redirect:/my-donations";
    }

    @GetMapping("/my-donations")
    public String getMyDonationsPage(Model model) {
        // TODO: find donations by user id


        model.addAttribute("donations", donationService.findAll());
        return "donation/my-donations";
    }
}
