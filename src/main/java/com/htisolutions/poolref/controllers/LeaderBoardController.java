package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.viewModels.LeaderBoardEntryViewModel;
import com.htisolutions.poolref.services.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/leaderboard")
public class LeaderBoardController {

    private LeaderBoardService leaderBoardService;

    @Autowired
    LeaderBoardController(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @RequestMapping()
    public ModelAndView index() {
        List<LeaderBoardEntryViewModel> leaderboardEntries = leaderBoardService.calculateLeaderBoard();

        ModelAndView model = new ModelAndView("views/leaderboard");
        model.addObject("leaderboardEntries", leaderboardEntries);
        return model;
    }

}
