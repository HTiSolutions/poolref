package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.SubLeaguesService;
import com.htisolutions.poolref.services.UserService;
import com.htisolutions.poolref.viewModels.CreateSubLeagueViewModel;
import com.htisolutions.poolref.viewModels.LeaderBoardEntryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sub-leagues")
public class LeagueController {

    private SubLeaguesService subLeaguesService;
    private UserService userService;

    @Autowired
    LeagueController(SubLeaguesService subLeaguesService, UserService userService) {
        this.subLeaguesService = subLeaguesService;
        this.userService = userService;
    }

    @RequestMapping()
    public ModelAndView index() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<List<LeaderBoardEntryViewModel>> subLeagues = subLeaguesService.calculateSubLeagues(user);
        ModelAndView model = new ModelAndView("views/sub-leagues");
        model.addObject("sub-leagues",subLeagues);
        return model;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createIndex(){

        CreateSubLeagueViewModel createSubLeague = new CreateSubLeagueViewModel();
        createSubLeague.setUsers(userService.getUsers());

        //Provide empty form for user to fill in (will contain list of users)

        return new ModelAndView("views/create-sub-league", "createLeague", createSubLeague);

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create() {
        // Take filled form data
        // Create league in the Db


        return new ModelAndView();
    }
}
