package com.htisolutions.poolref.viewModels;

public class ResultEntryViewModel {

    private String date;

    private String winnerNickname;

    private String loserNickname;

    public ResultEntryViewModel(String date, String winnerNickname, String loserNickname){
        this.date = date;
        this.winnerNickname = winnerNickname;
        this.loserNickname = loserNickname;
    }

    public String getDate() {
        return this.date;
    }

    public String getWinnerNickname() {
        return this.winnerNickname;
    }

    public String getLoserNickname() {
        return this.loserNickname;
    }

}
