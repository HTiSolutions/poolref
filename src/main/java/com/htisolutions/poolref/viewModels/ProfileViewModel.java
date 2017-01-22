package com.htisolutions.poolref.viewModels;

import com.htisolutions.poolref.entities.Game;

import java.util.List;

public class ProfileViewModel {

    private String firstName;

    private String lastName;

    private String nickname;

    private Integer gamesPlayed;

    private Integer wins;

    private Integer losses;

    private Float percentage;

    private List <Game> gamesInvolved;

    public ProfileViewModel(String firstName, String lastName, String nickname, Integer gamesPlayed, Integer wins, Integer losses, Float percentage, List<Game> gamesInvolved){
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.losses = losses;
        this.percentage = percentage;
        this.gamesInvolved = gamesInvolved;
    }

    public String getFirstName(){ return ("First name: " + firstName); }

    public String getLastName(){ return ("Last name: " + lastName); }

    public String getNickname(){ return ("Nickname: " + nickname); }

    public String getGamesPlayed(){
        String text = String.valueOf(gamesPlayed);
        return ("Games played: " + text);
    }

    public String getWins(){
        String text = String.valueOf(wins);
        return ("Number of wins: " + text);
    }

    public String getLosses(){
        String text = String.valueOf(losses);
        return ("Number of losses: " + text);
    }

    public String getPercentage(){
        String text = String.valueOf(percentage);
        return ("Win percentage: " + text + "%");
    }

    public List <Game> getGamesInvolved(){return gamesInvolved;}

}
