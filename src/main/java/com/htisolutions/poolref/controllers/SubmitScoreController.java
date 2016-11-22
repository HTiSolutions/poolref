package com.htisolutions.poolref.controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/submit-score")
public class SubmitScoreController {

    @RequestMapping("/greeting")
    public String submitScore(
            @RequestParam(value="winner") String winner,
            @RequestParam(value="loser") String loser)
    {
        return ("redirect:/greeting");
    }
}
