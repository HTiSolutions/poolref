package com.htisolutions.poolref.models;

import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.models.JSON.GameData;
import com.htisolutions.poolref.services.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameEntry {

    private Long gameId;

    private User winner;

    private User loser;

    private String date;

    private GameData gameData;

    public GameEntry(User winner, User loser, Game game){
        gameId = game.getId();
        this.winner = winner;
        this.loser = loser;
        date = new SimpleDateFormat("dd MMM yyyy  HH:mm").format(game.getDate());
    }

    public Long getGameId() { return gameId;}

    public String getDate(){
        return date;
    }

    public String getWinnerName(){return winner.formatName();}

    public String getLoserName(){return loser.formatName();}

    public Long getWinnerId(){ return winner.getId();}

    public Long getLoserId(){ return loser.getId();}

    public GameData getGameData() { return gameData; }

    public void setGameData(GameData gameData) { this.gameData = gameData; }

}
