package com.htisolutions.poolref.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping()
    public String index() {
        return "login";
    }
}
