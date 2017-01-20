package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.*;
import com.htisolutions.poolref.viewModels.LeaderboardEntryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class GreetingService {

    private GameService gameService;
    private UserService userService;

    @Autowired
    GreetingService(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

   public List<LeaderboardEntryViewModel> calculateLeaderboard (){
       HashMap<Long, LeaderboardEntryViewModel> leaderboard = new HashMap<>();
       Iterable<Game> games = gameService.getGames();

       for (Game game : games) {
           Long winnerId = game.getWinnerId();
           Long loserId = game.getLoserId();

           LeaderboardEntryViewModel winnerEntry = leaderboard.get(winnerId);
           LeaderboardEntryViewModel loserEntry = leaderboard.get(loserId);

           if (!leaderboard.containsKey(winnerId)) {
               User user = userService.getUserById(winnerId);
               LeaderboardEntryViewModel entry = new LeaderboardEntryViewModel(user.getNickname());
               entry.addWin();

               leaderboard.put(user.getId(), entry);
           } else if (!leaderboard.containsKey(loserId)) {
               User user = userService.getUserById(winnerId);
               LeaderboardEntryViewModel entry = new LeaderboardEntryViewModel(user.getNickname());
               entry.addLoss();

               leaderboard.put(user.getId(), entry);
           } else {
               winnerEntry.addWin();
               loserEntry.addLoss();
           }
       }

       List<LeaderboardEntryViewModel> leaderboardEntries = new ArrayList<LeaderboardEntryViewModel>(leaderboard.values());

       Comparator<LeaderboardEntryViewModel> leaderboardComparator = Comparator
               .comparing((LeaderboardEntryViewModel e)-> e.getPercentage())
               .thenComparing(e -> e.getWins());

       Collections.sort(leaderboardEntries, leaderboardComparator.reversed());

       int position = 1;
       for (LeaderboardEntryViewModel entry : leaderboardEntries) {
           entry.setPosition(position);
           position++;
       }

       return leaderboardEntries;
   }
}
