package com.htisolutions.poolref.viewModels;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.models.GameEntry;
import com.htisolutions.poolref.models.UserStat;

import java.util.List;

public class ProfileViewModel {

    private User user;

    private UserStat stat;

    private List <GameEntry> gamesInvolved;

    private Boolean enableDeleteButton;

    public ProfileViewModel(User user, UserStat stat, List<GameEntry> gamesInvolved, Boolean enableDeleteButton){
        this.user = user;
        this.stat = stat;
        this.gamesInvolved = gamesInvolved;
        this.enableDeleteButton = enableDeleteButton;
    }

    public String getFirstName(){ return ("First name: " + user.getForename()); }

    public String getLastName(){ return ("Last name: " + user.getSurname()); }

    public String getNickname(){ return ("Nickname: " + user.getNickname()); }

    public String getGamesPlayed(){
        String text = String.valueOf(stat.getGamesPlayed());
        return ("Games played: " + text);
    }

    public String getWins(){
        String text = String.valueOf(stat.getWins());
        return ("Number of wins: " + text);
    }

    public String getLosses(){
        String text = String.valueOf(stat.getLosses());
        return ("Number of losses: " + text);
    }

    public String getPercentage(){
        String text = String.valueOf(stat.getPercentage());
        return ("Win percentage: " + text + "%");
    }

    public List <GameEntry> getGamesInvolved(){return gamesInvolved;}

    public Boolean isDeleteButtonEnabled(){
       return(enableDeleteButton);
    }

}
