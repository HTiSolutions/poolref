package com.htisolutions.poolref.models;

import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameEntry {

    private Long gameId;

    private User winner;

    private User loser;

    private String date;

    public GameEntry(User winner, User loser, Game game){
        gameId = game.getId();
        this.winner = winner;
        this.loser = loser;
        date = new SimpleDateFormat("dd MMM yyyy  HH:mm").format(game.getDate());
    }

    public Long getGameId() { return gameId;}

    public String getWinner(){
        return winner.getNickname();
    }

    public String getLoser(){
        return loser.getNickname();
    }

    public String getDate(){
        return date;
    }
}
