package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.services.ResetPasswordService;
import com.htisolutions.poolref.viewModels.ResetPasswordNicknameViewModel;
import com.htisolutions.poolref.viewModels.ResetPasswordQuestionViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reset-password")
public class ResetPasswordController {

    private ResetPasswordService resetPasswordService;
    private String nickname;

    @Autowired
    ResetPasswordController(ResetPasswordService resetPasswordService){this.resetPasswordService = resetPasswordService;}

    @RequestMapping()
    public ModelAndView index() {
        ResetPasswordNicknameViewModel viewModel = new ResetPasswordNicknameViewModel();
        ModelAndView modelAndView = new ModelAndView("views/reset-password-nickname", "enterNickname", viewModel);
        modelAndView.addObject("resetPasswordNicknameViewModel", viewModel);
        return modelAndView;
    }

    @RequestMapping(value="/nickname", method=RequestMethod.POST)
    public String enteredNickname(@ModelAttribute(value="resetPasswordNicknameViewModel") ResetPasswordNicknameViewModel resetPasswordNicknameViewModel) {
        nickname = resetPasswordNicknameViewModel.getNickname();
        if (resetPasswordService.validName(nickname)) {
            return ("redirect:/reset-password/question");
        } else {
            return ("redirect:/reset-password?error");
        }
    }

    @RequestMapping(value="/question")
    public ModelAndView question() {
        ResetPasswordQuestionViewModel viewModel = new ResetPasswordQuestionViewModel();
        viewModel.setQuestion(resetPasswordService.getQuesetionForNickname(nickname));
        ModelAndView modelAndView = new ModelAndView("views/reset-password-question", "enterQuestion", viewModel);
        modelAndView.addObject("resetQuestionViewModel", viewModel);
        return modelAndView;
    }

    @RequestMapping(value="/password", method=RequestMethod.POST)
    public String enteredQuestion(@ModelAttribute(value="resetPasswordNicknameViewModel") ResetPasswordNicknameViewModel resetPasswordNicknameViewModel) {
        nickname = resetPasswordNicknameViewModel.getNickname();
        if (resetPasswordService.validName(nickname)) {
            return ("redirect:/security-question");
        } else {
            return ("redirect:/reset-password?error");
        }
    }
}