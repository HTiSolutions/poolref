package com.htisolutions.poolref.controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping()
    public String index() {
        return "login";
    }

    @RequestMapping("/signin")
    public String signIn(
            @RequestParam(value="email") String email,
            @RequestParam(value="password") String password)
    {
        return ("redirect:/greeting");
    }
}
