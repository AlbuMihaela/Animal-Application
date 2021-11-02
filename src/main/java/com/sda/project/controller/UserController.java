package com.sda.project.controller;

import com.sda.project.dto.UserDto;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        // "userDto" will be used in html
        // userDto object represents the java instance
        model.addAttribute("userDto", userDto);
        // folder / page name
        return "user/register";
    }

    @PostMapping("/register/add")
    public String register(@ModelAttribute("userDto") UserDto userDto) {
        userService.save(userDto);
        return "redirect:/login";
    }

    // get page
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "login";
    }

    // action
    @PostMapping("/login")
    public String login(@ModelAttribute("userDto") UserDto userDto) {
        userService.findByEmail(userDto.getEmail());
        return "redirect: /home";
    }

    @GetMapping("/admin")
    public String getAdminPage(){
      return "admin/admin";
    }
}





