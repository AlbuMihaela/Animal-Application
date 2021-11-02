package com.sda.project.controller;

import com.sda.project.dto.DonationDto;
import com.sda.project.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DonationController {

    private final DonationService donationService;

    @Autowired
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/donations")
    public String getDonationsPage(Model model) {
        model.addAttribute("donations", donationService.findAll());
        return "donation/donations";
    }

    @GetMapping("/donation-add")
    public String getDonationsForm(Model model) {
        model.addAttribute("donationDto", new DonationDto());
//        model.addAttribute("products", new Product[5]);
        //TODO ask Cosmin how to incorporate enums in html
        return "donation/donation-add";
    }

    //TODO why can't we add donation to db(submit doesn't work)
    @PostMapping("donations/add")
    public String addDonationForm(@ModelAttribute("donationDto") DonationDto donationDto) {
        donationService.save(donationDto);
//        model.addAttribute("donationDto", donationDto);
        return "redirect:/home";
    }


}
