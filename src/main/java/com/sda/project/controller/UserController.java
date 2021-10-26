package com.sda.project.controller;

import com.sda.project.dto.UserDto;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // mapping
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        // folder / page name
        return "user/register";
    }

    @PostMapping("/register")
    public String postRegisterPage(@ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.save(userDto);
            return "redirect:/login";
        }
        return "user/register";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "user/login";
    }

    @PostMapping("/login")
    public String postLoginPage(@ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return "redirect:/user";
        }
        return "user/login";
    }

    @GetMapping("/landing")
    public String getLandingPage() {
        return "landing";
    }

    @GetMapping("/user")
    public String getUserPage() {
        return "user/user";
    }

    @GetMapping("/donate")
    public String getDonatePage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "user/donate";
    }

    @PostMapping("/donate")
    public String postDonatePage(@ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return "redirect:/users";
        }
        return "return/donate";
    }

    @GetMapping("/adopt")
    public String getAdoptPage() {
        return "user/adopt";
    }

    @PostMapping("/adopt")
    public String postAdoptPage() {
        return "user/adopt";
    }

    @GetMapping("/transfer")
    public String getTransferPage() {
        return "user/transfer";
    }
}


