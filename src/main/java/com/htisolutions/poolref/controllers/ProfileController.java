package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.GameService;
import com.htisolutions.poolref.services.ProfileService;
import com.htisolutions.poolref.services.UserService;
import com.htisolutions.poolref.viewModels.ProfileViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(
            @RequestParam(name = "id", required = false) Long userId
    ) {

        User user = (userId == null)
                ? (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                : userService.getUserById(userId);

        ProfileViewModel profileViewModel = profileService.generateProfile(user);

        return new ModelAndView("views/profile", "profile", profileViewModel);
    }
}
