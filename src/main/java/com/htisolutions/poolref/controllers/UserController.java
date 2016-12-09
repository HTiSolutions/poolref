package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.models.User;
import com.htisolutions.poolref.models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserDao userDao;

    @Autowired
    UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("")
    public ModelAndView index(Long id,  Model model) {
        User currentUser = userDao.findOne(id);

        ModelAndView view = new ModelAndView("user/index");
        view.addObject("user", currentUser);
        return view;
    }

    @RequestMapping("/create")
    public ModelAndView create(String forename, String surname, String email, String hashedpassword) {

        String userId = "";
        try {
            User user = new User(forename, surname, email, hashedpassword);
            userDao.save(user);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            //"Error creating the user: " + ex.toString();
        }

        ModelAndView view = new ModelAndView(new RedirectView(""));
        view.addObject("id", userId);
        return view;
    }

}
