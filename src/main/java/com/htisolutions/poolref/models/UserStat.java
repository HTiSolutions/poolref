package com.htisolutions.poolref.models;

import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.User;

public class UserStat {

    private User user;

    private Integer wins;

    private Integer losses;

    private Integer gamesPlayed;

    private Float percentage;

    public UserStat(User user, Iterable <Game> games){
        this.user = user;
        wins = 0;
        losses = 0;
        for (Game game : games){
            if (game.getWinnerId() == user.getId()){
                wins++;
            }
            if (game.getLoserId() == user.getId()){
                losses++;
            }
        }
        gamesPlayed = wins + losses;
        if (gamesPlayed > 0) {
            percentage = ((float) wins / ((float) wins + (float) losses)) * 100;
        }else{
            percentage = 0.0f;
        }
    }
    public User getUser(){return user;}
    public Integer getWins(){ return wins;}

    public Integer getLosses(){ return losses;}

    public Integer getGamesPlayed(){ return gamesPlayed;}

    public Float getPercentage(){ return percentage;}
}
