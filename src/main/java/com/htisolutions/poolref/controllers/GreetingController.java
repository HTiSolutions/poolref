package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.models.Game;
import com.htisolutions.poolref.models.LeaderboardEntry;
import com.htisolutions.poolref.services.GameService;
import com.htisolutions.poolref.services.GreetingService;
import com.htisolutions.poolref.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/greeting")
public class GreetingController {

    private UserService userService;
    private GreetingService greetingService;

    @Autowired
    GreetingController(UserService userService, GreetingService greetingService) {
        this.userService = userService;
        this.greetingService = greetingService;
    }

    @RequestMapping()
    public ModelAndView index() {

        List<LeaderboardEntry> leaderboardEntries = greetingService.calculateLeaderboard();
        System.out.println(leaderboardEntries.size());
        ModelAndView model = new ModelAndView("views/greeting");
        model.addObject("leaderboardEntries",leaderboardEntries);
        model.addObject("userService", userService);

        return model;
    }

}
