package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.SubLeaguesService;
import com.htisolutions.poolref.viewModels.SubLeagueViewModel;
import com.htisolutions.poolref.services.UserService;
import com.htisolutions.poolref.viewModels.CreateSubLeagueViewModel;
import com.htisolutions.poolref.viewModels.LeaderBoardEntryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

@Controller
@RequestMapping("/sub-leagues")
public class LeagueController {

    private SubLeaguesService subLeaguesService;
    private UserService userService;
    private long leagueId;

    @Autowired
    LeagueController(SubLeaguesService subLeaguesService, UserService userService) {
        this.subLeaguesService = subLeaguesService;
        this.userService = userService;
        this.leagueId = 0;
    }

    @RequestMapping()
    public ModelAndView index() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, List<LeaderBoardEntryViewModel>> subLeagues = subLeaguesService.calculateSubLeagues(user);
        return new ModelAndView("views/sub-leagues", "sub", subLeagues);
    }

    @RequestMapping(value = "/name1")
    public ModelAndView name() {
      CreateSubLeagueViewModel createSubLeague = new CreateSubLeagueViewModel();
      return new ModelAndView("views/name-league", "createLeague", createSubLeague);
    }

    @RequestMapping(value = "/name2", method = RequestMethod.POST)
    public ModelAndView submitName(@ModelAttribute(value="createLeague") CreateSubLeagueViewModel createSubLeague, @RequestParam(name="name") String name){
        if(!createSubLeague.getName().isEmpty()){
            this.leagueId = subLeaguesService.createLeague(createSubLeague.getName());
        if (leagueId != 0){
          return createIndex(createSubLeague);
        }
      }
      return createIndex(createSubLeague);
    }

    @RequestMapping(value = "/create1")
    public ModelAndView createIndex(@ModelAttribute(value="createLeague") CreateSubLeagueViewModel createSubLeague){
      createSubLeague.setAllUsers(userService.getUsers());
      if (subLeaguesService.getLeagueName(leagueId) != null) {
        createSubLeague.setName(subLeaguesService.getLeagueName(leagueId));
      }
      if(leagueId != 0) {
        if(subLeaguesService.getPlayers(leagueId) != null) {
          createSubLeague.setPlayers(subLeaguesService.getPlayers(leagueId));
        }
      }
      return new ModelAndView("views/create-sub-league", "createLeague", createSubLeague);
    }

    @RequestMapping(value = "/getPlayers", method = RequestMethod.POST)
    public @ResponseBody String getPlayers(@RequestBody ArrayList<String> players){
        List<Boolean> result = new ArrayList<Boolean>();
        for(String player : players) {
            User p = new User();
            for(User user : userService.getUsers()) {
              if(player.equals(user.getNickname())) {
                p = user;
                result.add(subLeaguesService.addPlayers(leagueId, p.getId()));
              }
            }
        }
        return "success";
    }
}
