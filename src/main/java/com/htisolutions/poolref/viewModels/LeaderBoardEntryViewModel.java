package com.htisolutions.poolref.viewModels;

import java.util.*;

public class LeaderBoardEntryViewModel {

    private Integer position;
    
    private String nickname;

    private Integer wins;

    private Integer losses;

    private Float percentage;

    public LeaderBoardEntryViewModel(String nickname){
        this.nickname = nickname;
        wins = 0;
        losses = 0;
        percentage = 0.0f;
    }

    public void addWin(){
        wins++;
        setPercentage();
    }

    public void addLoss(){
        losses++;
        setPercentage();
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    private void setPercentage(){
        percentage = ((float) wins / ((float)wins + (float)losses)) * 100;
    }

    public Integer getPosition() {
        return this.position;
    }

    public String getNickname() { return this.nickname; }

    public Integer getWins() { return this.wins; }

    public Integer getLosses() { return this.losses; }

    public Float getPercentage() { return this.percentage; }

}
