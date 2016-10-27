package com.htisolutions.poolref.controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {


    @RequestMapping("/login")
    public String index() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/greeting/registered")
    public String registered(
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

    @RequestMapping("/login/signin")
    public String signin(
            @RequestParam(value="email") String email,
            @RequestParam(value="password") String password)
    {
        return ("redirect:/greeting");
    }
}
