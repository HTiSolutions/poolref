package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.security.CustomAuthenticationProvider;
import com.htisolutions.poolref.services.RegisterService;
import com.htisolutions.poolref.services.SecurityQuestionService;
import com.htisolutions.poolref.viewModels.RegisterViewModel;
import com.htisolutions.poolref.viewModels.SecurityQuestionViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/security-question")
public class SecurityQuestionController {

    private SecurityQuestionService securityQuestionService;

    @Autowired
    SecurityQuestionController (SecurityQuestionService securityQuestionService) { this.securityQuestionService = securityQuestionService; }

    @RequestMapping()
    public ModelAndView index() {
        SecurityQuestionViewModel securityQuestionViewModel = new SecurityQuestionViewModel();
        securityQuestionViewModel.setSecurityQuestions(securityQuestionService.getOptions());
        ModelAndView modelAndView = new ModelAndView("views/security-question", "securityQuestions", securityQuestionViewModel);
        modelAndView.addObject("securityQuestionViewModel", securityQuestionViewModel);
        return modelAndView;
    }

    @RequestMapping(value="/security-question", method=RequestMethod.POST)
    public String register(@ModelAttribute(value="securityQuestionViewModel") SecurityQuestionViewModel securityQuestionViewModel) {
        String answer = securityQuestionViewModel.getAnswer();
        String confirmAnswer = securityQuestionViewModel.getConfirmAnswer();
        long questionId = securityQuestionViewModel.getQuestionId();
        if (securityQuestionService.validateQuestion(answer, confirmAnswer, questionId)) {
            return ("redirect:/leaderboard");
        } else {
            return ("redirect:/security-question?error");
        }
    }

}
