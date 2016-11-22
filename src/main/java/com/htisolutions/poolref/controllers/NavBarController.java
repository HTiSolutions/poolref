package com.htisolutions.poolref.controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NavBarController {

    @RequestMapping("/greeting")
    public String home() { return "greeting"; }

    @RequestMapping("/profile")
    public String profile() {
        return "profile";
    }

    @RequestMapping("/leaderboard")
    public String leaderboard() { return "leaderboard"; }

    @RequestMapping("/submit-score")
    public String submitScore() { return "submit-score"; }

}
