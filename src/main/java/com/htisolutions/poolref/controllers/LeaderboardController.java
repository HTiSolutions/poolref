package com.htisolutions.poolref.controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/leaderboard")
public class LeaderboardController {

    @RequestMapping()
    public String index() { return "views/leaderboard"; }

}
