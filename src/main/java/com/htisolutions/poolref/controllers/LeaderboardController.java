package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.models.UserDao;
import com.htisolutions.poolref.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.htisolutions.poolref.services.GameService;
import com.htisolutions.poolref.models.Game;

@Controller
@RequestMapping("/leaderboard")
public class LeaderboardController {

    private GameService gameService;
    private UserService userService;

    @Autowired
    LeaderboardController(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @RequestMapping()
    public ModelAndView index() {

        Iterable<Game> games = gameService.getGames();

        ModelAndView model = new ModelAndView("views/leaderboard");
        model.addObject("games",games);
        model.addObject("userService", userService);
        return model;
    }

}
