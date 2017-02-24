package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.ProfileService;
import com.htisolutions.poolref.services.SubLeagueService;
import com.htisolutions.poolref.services.UserService;
import com.htisolutions.poolref.viewModels.LeaderBoardEntryViewModel;
import com.htisolutions.poolref.viewModels.ProfileViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private ProfileService profileService;
    private SubLeagueService subLeagueService;

    @Autowired
    ProfileController(ProfileService profileService, SubLeagueService subLeagueService) {
        this.profileService = profileService;
        this.subLeagueService = subLeagueService;
    }

    @RequestMapping()
    public ModelAndView index() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProfileViewModel profile = profileService.generateProfile(user);

        ModelAndView model = new ModelAndView("views/profile");
        model.addObject("profile", profile);
        return model;
    }

    @RequestMapping("/sub-league")
    public ModelAndView subLeague(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<List<LeaderBoardEntryViewModel>> subLeagues = subLeagueService.calculateSubLeageues();
        ModelAndView model = new ModelAndView("views/sub-league");
        model.addObject("sub-leagues",subLeagues);
        return model;
    }

}
