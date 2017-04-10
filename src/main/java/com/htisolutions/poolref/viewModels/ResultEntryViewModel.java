package com.htisolutions.poolref.viewModels;

import com.htisolutions.poolref.entities.User;

public class ResultEntryViewModel {

    private String date;

    private User winner;

    private User loser;

    public ResultEntryViewModel(String date, User winner, User loser){
        this.date = date;
        this.winner = winner;
        this.loser = loser;
    }

    public String getDate() {
        return this.date;
    }

    public String getWinnerName() {
        return winner.formatName();
    }

    public String getLoserName() {
        return loser.formatName();
    }

    public Long getWinnerId(){return winner.getId();}

    public Long getLoserId(){return loser.getId();}


}
