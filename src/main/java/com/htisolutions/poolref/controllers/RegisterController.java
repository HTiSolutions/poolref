package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/register")
public class RegisterController {

    private RegisterService registerService;

    @Autowired
    RegisterController (RegisterService registerService) {
        this.registerService = registerService;
    }

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

        if (registerService.validRegister(firstName, lastName, registerEmail, registerPassword, confirmPassword)) {
            return ("redirect:/greeting");
        } else {
            return ("redirect:/register?error");
        }
    }
}
