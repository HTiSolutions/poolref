package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.services.ResultsService;
import com.htisolutions.poolref.services.UserService;
import com.htisolutions.poolref.viewModels.ResultEntryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.htisolutions.poolref.services.GameService;
import com.htisolutions.poolref.entities.Game;

@Controller
@RequestMapping("/results")
public class ResultsController {

    private ResultsService resultsService;

    @Autowired
    ResultsController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @RequestMapping()
    public ModelAndView index() {

        Iterable<ResultEntryViewModel> results = resultsService.getResults();

        ModelAndView model = new ModelAndView("views/results");
        model.addObject("results", results);
        return model;
    }

}
