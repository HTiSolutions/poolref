package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.models.User;
import com.htisolutions.poolref.models.UserDao;
import com.htisolutions.poolref.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @RequestMapping("/signin")
    public String signIn(
            @RequestParam(value="email") String email,
            @RequestParam(value="password") String password)
    {
        if(loginService.validLogin(email, password)){
            return ("redirect:/greeting");
        }else{
            return ("redirect:/login");
        }
    }

}
