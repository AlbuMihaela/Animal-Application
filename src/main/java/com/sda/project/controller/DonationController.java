package com.sda.project.controller;

import com.sda.project.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class DonationController {


    // FIXME: implement later
    @GetMapping("/donations")
    public String getDonatePage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "donation";
    }

    // FIXME: implement later
    @PostMapping("/donations")
    public String postDonatePage(@ModelAttribute("userDto") UserDto userDto) {
        return "redirect:/user";
    }
}
