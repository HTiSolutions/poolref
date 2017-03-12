package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.SubLeaguesService;
import com.htisolutions.poolref.viewModels.LeaderBoardEntryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sub-leagues")
public class SubLeaguesController {

    private SubLeaguesService subLeaguesService;

    @Autowired
    SubLeaguesController(SubLeaguesService subLeaguesService) {
        this.subLeaguesService = subLeaguesService;
    }

    @RequestMapping()
    public ModelAndView index() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<List<LeaderBoardEntryViewModel>> subLeagues = subLeaguesService.calculateSubLeagues(user);
        ModelAndView model = new ModelAndView("views/sub-leagues");
        model.addObject("sub-leagues",subLeagues);
        return model;
    }
}
