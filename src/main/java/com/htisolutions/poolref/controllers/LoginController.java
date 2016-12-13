package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    private LoginService loginService;

    @Autowired
    LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping()
    public String index() {
        return "login";
    }
}
