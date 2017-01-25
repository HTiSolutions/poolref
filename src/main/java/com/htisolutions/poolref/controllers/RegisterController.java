package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.security.CustomAuthenticationProvider;
import com.htisolutions.poolref.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/register")
public class RegisterController {

    private RegisterService registerService;

    @Autowired
    RegisterController (RegisterService registerService) { this.registerService = registerService; }

    @RequestMapping()
    public String index() {
        return "register";
    }

    @RequestMapping("/register")
    public String register(
            @RequestParam(value = "first-name") String firstName,
            @RequestParam(value = "last-name") String lastName,
            @RequestParam(value = "register-nickname") String registerNickname,
            @RequestParam(value = "register-password") String registerPassword,
            @RequestParam(value = "confirm-password") String confirmPassword) {

        if (registerService.validRegister(firstName, lastName, registerNickname, registerPassword, confirmPassword)) {
            registerService.autologin(registerNickname, registerPassword);
            return ("redirect:/leaderboard");
        } else {
            return ("redirect:/register?error");
        }
    }

}
