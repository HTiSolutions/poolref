package com.htisolutions.poolref.iDontKnowWhereToPutThese;

import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserStat {

    private Integer wins;

    private Integer losses;

    private Integer gamesPlayed;

    private Float percentage;

    public UserStat(Long userId, Iterable <Game> games){
        wins = 0;
        losses = 0;
        for (Game game : games){
            if (game.getWinnerId() == userId){
                wins++;
            }
            if (game.getLoserId() == userId){
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

    public Integer getWins(){ return wins;}

    public Integer getLosses(){ return losses;}

    public Integer getGamesPlayed(){ return gamesPlayed;}

    public Float getPercentage(){ return percentage;}
}
