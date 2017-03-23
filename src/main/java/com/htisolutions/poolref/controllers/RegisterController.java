package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.security.CustomAuthenticationProvider;
import com.htisolutions.poolref.services.RegisterService;
import com.htisolutions.poolref.viewModels.RegisterViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
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
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("register");
        RegisterViewModel registerViewModel = new RegisterViewModel();
        modelAndView.addObject("registerViewModel", registerViewModel);
        return modelAndView;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String register(@ModelAttribute(value="registerViewModel") RegisterViewModel registerViewModel) {
        String firstName = registerViewModel.getFirstName();
        String lastName = registerViewModel.getLastName();
        String registerNickname = registerViewModel.getRegisterNickname();
        String registerPassword = registerViewModel.getRegisterPassword();
        String confirmPassword = registerViewModel.getConfirmPassword();


        if (registerService.validRegister(firstName, lastName, registerNickname, registerPassword, confirmPassword)) {
            registerService.autologin(registerNickname, registerPassword);
            return ("redirect:/leaderboard");
        } else {
            return ("redirect:/register?error");
        }
    }

}
