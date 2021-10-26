package com.sda.project.controller;

import com.sda.project.dto.UserDto;
import com.sda.project.service.UserService;
import com.sda.project.validator.UserValidator;
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
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @GetMapping( "/register")
    public String getRegisterPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
                return "register";
    }

    @PostMapping( "/register")
    public String postRegisterPage(@ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        userValidator.validate(userDto, bindingResult);
        if (!bindingResult.hasErrors()) {
            userService.addUser(userDto);
            return "redirect:/login";
        }
        return "register";
    }

    @GetMapping( "/login")
    public String getLoginPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "login";
    }

    @PostMapping( "/login")
    public String postLoginPage(@ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        userValidator.validateLogin(userDto, bindingResult);
        if (!bindingResult.hasErrors()) {
            return "redirect:/user";
        }
        return "login";
    }

    @GetMapping( "/landing")
    public String getLandingPage() {
        return "landing";
    }

    @GetMapping( "/user")
    public String getUserPage() {
        return "user";
    }

    @GetMapping( "/donate")
    public String getDonatePage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "donate";
    }

    @PostMapping("/donate")
    public String postDonatePage(@ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        userValidator.validateLogin(userDto, bindingResult);
        if (!bindingResult.hasErrors()) {
            return "redirect:/users";
        }
        return "donate";
    }

    @GetMapping( "/adopt")
    public String getAdoptPage() {
        return "adopt";
    }

    @PostMapping("/adopt")
    public String postAdoptPage() {
        return "adopt";
    }

    @GetMapping( "/transfer")
    public String getTransferPage() {
        return "transfer";
    }
}


