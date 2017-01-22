package com.htisolutions.poolref.models;

import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by george on 22/01/2017.
 */
public class GameEntry {

    private User winner;

    private User loser;

    private String date;

    public GameEntry(UserService userService, Game game){
        Long winnerId = game.getWinnerId();
        Long loserId = game.getLoserId();
        winner = userService.getUserById(winnerId);
        loser = userService.getUserById(loserId);
        date = new SimpleDateFormat("dd MMM yyyy  HH:mm").format(game.getDate());
    }

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
