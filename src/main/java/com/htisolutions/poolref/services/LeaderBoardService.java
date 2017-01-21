package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.*;
import com.htisolutions.poolref.viewModels.LeaderBoardEntryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class LeaderBoardService {

    private GameService gameService;
    private UserService userService;

    @Autowired
    LeaderBoardService(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

   public List<LeaderBoardEntryViewModel> calculateLeaderBoard(){
       HashMap<Long, LeaderBoardEntryViewModel> leaderBoard = new HashMap<>();
       Iterable<Game> games = gameService.getGames();

       for (Game game : games) {
           Long winnerId = game.getWinnerId();
           Long loserId = game.getLoserId();

           if (leaderBoard.containsKey(winnerId)) {
               LeaderBoardEntryViewModel winnerEntry = leaderBoard.get(winnerId);
               winnerEntry.addWin();
           } else {
               User user = userService.getUserById(winnerId);
               LeaderBoardEntryViewModel entry = new LeaderBoardEntryViewModel(user.getNickname());
               entry.addWin();

               leaderBoard.put(user.getId(), entry);
           }

           if (leaderBoard.containsKey(loserId)) {
               LeaderBoardEntryViewModel loserEntry = leaderBoard.get(loserId);
               loserEntry.addLoss();
           } else {
               User user = userService.getUserById(loserId);
               LeaderBoardEntryViewModel entry = new LeaderBoardEntryViewModel(user.getNickname());
               entry.addLoss();

               leaderBoard.put(user.getId(), entry);
           }
       }

       List<LeaderBoardEntryViewModel> leaderBoardEntries = new ArrayList<LeaderBoardEntryViewModel>(leaderBoard.values());

       Comparator<LeaderBoardEntryViewModel> leaderBoardComparator = Comparator
               .comparing((LeaderBoardEntryViewModel e)-> e.getPercentage())
               .thenComparing(e -> e.getWins());

       Collections.sort(leaderBoardEntries, leaderBoardComparator.reversed());

       int position = 1;
       for (LeaderBoardEntryViewModel entry : leaderBoardEntries) {
           entry.setPosition(position);
           position++;
       }

       return leaderBoardEntries;
   }
}
