package com.htisolutions.poolref.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/settings")
public class SettingsController {
    @RequestMapping()
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("views/settings");
        return modelAndView;
    }
}
