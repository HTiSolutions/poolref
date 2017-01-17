package com.htisolutions.poolref.services;

import com.htisolutions.poolref.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class GreetingService {

    private GameService gameService;
    private UserService userService;

    @Autowired
    GreetingService(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

   public List<LeaderboardEntry> calculateLeaderboard (){
       List <LeaderboardEntry> leaderboard = new ArrayList();
       Iterable<Game> games = gameService.getGames();
       Iterable<User> users = userService.getUsers();

       for (User user : users){
           Long id = user.getId();
           String nickname = user.getNickname();
           leaderboard.add(new LeaderboardEntry(id, nickname));
       }

       for (Game game : games){
           Long winnerId = game.getWinnerId();
           Long loserId = game.getLoserId();
           LeaderboardEntry winnerEntry = searchLeaderboardById(winnerId, leaderboard);
           LeaderboardEntry loserEntry = searchLeaderboardById(loserId, leaderboard);
           winnerEntry.addWin();
           loserEntry.addLoss();
       }
       leaderboard = removeUnusedUsers(leaderboard);
       Collections.sort(leaderboard, new Comparator<LeaderboardEntry>() {
           @Override
           public int compare(LeaderboardEntry o1, LeaderboardEntry o2) {
               if (o1.getPercentage() < o2.getPercentage()){
                   return -1;
               }
               else if (o1.getPercentage() > o2.getPercentage()){
                   return 1;
               }
               else{
                   if (o1.getWins() < o2.getWins()){
                       return -1;
                   }
                   else if (o1.getWins() > o2.getWins()){
                       return 1;
                   }
                   else{
                       return 0;
                   }
               }
           }
       });
       return leaderboard;
   }

   private LeaderboardEntry searchLeaderboardById (Long id, List <LeaderboardEntry> leaderboard){
       for (LeaderboardEntry entry : leaderboard){
           if (entry.getId() == id){
               return entry;
           }
       }

       return null;
   }

   private List <LeaderboardEntry> removeUnusedUsers(List <LeaderboardEntry> leaderboard){
       for (LeaderboardEntry entry : leaderboard){
           if(entry.getWins() + entry.getLosses() <= 0){
               leaderboard.remove(entry);
           }
       }
       return leaderboard;
   }

}
