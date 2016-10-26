package com.htisolutions.poolref.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingController {

    @RequestMapping("/greeting")
    public String index(
            @RequestParam(value="name", required=false, defaultValue="World") String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "greeting";
    }

}
