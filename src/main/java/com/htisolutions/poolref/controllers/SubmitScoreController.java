package com.htisolutions.poolref.controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/submit-score")
public class SubmitScoreController {

    @RequestMapping()
    public String index() { return "views/submit-score"; }

}
