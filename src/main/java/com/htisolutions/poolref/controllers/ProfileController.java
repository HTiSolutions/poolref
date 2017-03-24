package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.GameService;
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
    private GameService gameService;

    @Autowired
    ProfileController(ProfileService profileService, GameService gameService) {
        this.profileService = profileService;
        this.gameService = gameService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProfileViewModel viewModel = profileService.generateProfile(user);

        return new ModelAndView("views/profile", "profile", viewModel);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Long id) {

        gameService.gameDelete(id);

        return ("redirect:/profile");
    }
}
