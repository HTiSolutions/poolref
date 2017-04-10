package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.TwitterService;
import com.htisolutions.poolref.services.UserService;
import com.htisolutions.poolref.services.GameService;
import com.htisolutions.poolref.viewModels.SubmitScoreViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/submit-score")
public class SubmitScoreController {

    private GameService gameService;
    private UserService userService;
    private TwitterService twitterService;

    @Autowired
    SubmitScoreController(UserService userService, GameService gameService, TwitterService twitterService) {
        this.userService = userService;
        this.gameService = gameService;
        this.twitterService = twitterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {

        SubmitScoreViewModel viewModel = new SubmitScoreViewModel();
        viewModel.setUsers(userService.getUsers());

        return new ModelAndView("views/submit-score", "submitScore", viewModel);
    }

    @RequestMapping(value = "/submit-score", method = RequestMethod.POST)
    public String submit(@ModelAttribute(value="submitScore") SubmitScoreViewModel model) {
        if(gameService.gameSave(new Date(), model.getWinner().getId(), model.getLoser().getId())) {
            if (twitterService.postToTwitter(model.getTweet(), model.getTagOpponent(),model.getWinner(), model.getLoser())){
                return ("redirect:/submit-score");
            }else{
                return ("redirect:/submit-score?twitterError");
            }
        } else {
            return ("redirect:/submit-score?error");
        }
    }

}
