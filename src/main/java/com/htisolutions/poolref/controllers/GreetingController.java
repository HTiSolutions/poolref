package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.viewModels.LeaderboardEntryViewModel;
import com.htisolutions.poolref.services.GreetingService;
import com.htisolutions.poolref.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/greeting")
public class GreetingController {

    private GreetingService greetingService;

    @Autowired
    GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping()
    public ModelAndView index() {

        List<LeaderboardEntryViewModel> leaderboardEntries = greetingService.calculateLeaderboard();

        ModelAndView model = new ModelAndView("views/greeting");
        model.addObject("leaderboardEntries", leaderboardEntries);
        return model;
    }

}
