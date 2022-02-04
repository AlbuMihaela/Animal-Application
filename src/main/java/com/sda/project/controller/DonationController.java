package com.sda.project.controller;

import com.sda.project.dto.AddDonation;
import com.sda.project.dto.DonationInfo;
import com.sda.project.model.Product;
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

import java.util.HashSet;
import java.util.Set;

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
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedUser = userService.findByEmail(email);
        Long loggedUserId = loggedUser.getId();
        AddDonation donation = new AddDonation(loggedUserId, null, null, null);
        model.addAttribute("donation", donation);
        model.addAttribute("loggedUser", loggedUser);
        return "donation/donation-add";
    }

    @PostMapping("donations/add")
    public String add(@ModelAttribute("donation") AddDonation addDonation) {
        if (addDonation.getProduct().equals(Product.MONEY)) {
            addDonation.setDetails("transfer");
            donationService.save(addDonation);
            return "redirect:/transfers/add";
        } else {
            donationService.save(addDonation);
            return "redirect:/home";
        }

    }

    @GetMapping("/my-donations")
    public String getMyDonationsPage(Model model) {
        User user = userService.findLoggedUser();
        Set<DonationInfo> donations = donationService.findDonationByUser(user);
        model.addAttribute("myDonations", donations);
        return "donation/my-donations";
    }

    @GetMapping("/donations/about")
    public String getDonationsAboutPage() {
        return "donation/donations-about";
    }
}
