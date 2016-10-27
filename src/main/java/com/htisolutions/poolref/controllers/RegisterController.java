package com.htisolutions.poolref.controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @RequestMapping()
    public String index() {
        return "register";
    }

    @RequestMapping("/register")
    public String register(
            @RequestParam(value = "first-name") String firstName,
            @RequestParam(value = "last-name") String lastName,
            @RequestParam(value = "register-email") String registerEmail,
            @RequestParam(value = "register-password") String registerPassword,
            @RequestParam(value = "confirm-password") String confirmPassword) {

        if (registerPassword.equals(confirmPassword)) {
            return "redirect:/greeting";
        }
        else {
            return "redirect:/register";
        }
    }
}