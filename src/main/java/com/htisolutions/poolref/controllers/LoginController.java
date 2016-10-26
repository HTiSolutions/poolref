package com.htisolutions.poolref.controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {



    @RequestMapping("/login")
    public String index() {
        return "login";
    }



    @RequestMapping("/login/signin")
    public String signin(
            @RequestParam(value="email") String email,
            @RequestParam(value="password") String password)
    {
        return ("redirect:/greeting");
    }
}
