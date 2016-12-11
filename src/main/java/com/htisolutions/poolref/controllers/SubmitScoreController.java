package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.models.User;
import com.htisolutions.poolref.services.UserService;
import com.htisolutions.poolref.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;

@Controller
@RequestMapping("/submit-score")
public class SubmitScoreController {

    private GameService gameService;
    private UserService userService;

    @Autowired
    SubmitScoreController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @RequestMapping()
    public ModelAndView index() {
        Iterable<String> userEmails = userService.getUserEmails();

        ModelAndView model = new ModelAndView("views/submit-score");
        model.addObject("userEmails",userEmails);
        return model;
    }

    @RequestMapping("/submit-score")
    public String submit(String winner, String loser) {
            gameService.gameSave(winner, loser);
            return ("redirect:/submit-score");
    }

}
