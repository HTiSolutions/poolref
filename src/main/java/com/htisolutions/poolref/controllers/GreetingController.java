package com.htisolutions.poolref.controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/greeting")
public class GreetingController {

    @RequestMapping()
    public String index() { return "views/greeting"; }

}
