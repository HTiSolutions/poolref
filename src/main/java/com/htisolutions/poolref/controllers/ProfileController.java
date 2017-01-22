package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.ProfileService;
import com.htisolutions.poolref.services.UserService;
import com.htisolutions.poolref.viewModels.ProfileViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private ProfileService profileService;
    private UserService userService;

    @Autowired
    ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @RequestMapping()
    public ModelAndView index() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProfileViewModel profile = profileService.generateProfile(user);
        System.out.println(profile.getFirstName());

        ModelAndView model = new ModelAndView("views/profile");
        model.addObject("profile", profile);
        model.addObject("userService", userService);
        return model;
    }

}
