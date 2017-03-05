package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.UserService;
import com.htisolutions.poolref.viewModels.CreateSubLeagueViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/create-sub-league")
public class CreateSubLeagueController {

    private UserService userService;

    @Autowired
    CreateSubLeagueController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {

      CreateSubLeagueViewModel createSubLeague = new CreateSubLeagueViewModel();
      createSubLeague.setUsers(userService.getUsers());

      return new ModelAndView("views/create-sub-league", "createSubLeague", createSubLeague);
    }
}
