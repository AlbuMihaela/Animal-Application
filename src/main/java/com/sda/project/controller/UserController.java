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

    @GetMapping(value = "/registration")
    public String getRegistrationPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        System.out.println("registration");
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String postRegistrationPage(@ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        userValidator.validate(userDto, bindingResult);
        if (!bindingResult.hasErrors()) {
            userService.addUser(userDto);
            return "redirect:/login";
        }
        return "registration";
    }

    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "login";
    }

    @PostMapping(value = "/login")
    public String postLoginPage(@ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        userValidator.validateLogin(userDto, bindingResult);
        if (!bindingResult.hasErrors()) {
            return "redirect:/user";
        }
        return "login";
    }

    @GetMapping(value = "/landing")
    public String getLandingPage() {
        return "landing";
    }

    @GetMapping(value = "/user")
    public String getUserPage() {
        return "user";
    }


}
