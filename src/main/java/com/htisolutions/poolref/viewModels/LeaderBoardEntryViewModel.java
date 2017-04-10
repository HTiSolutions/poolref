package com.htisolutions.poolref.viewModels;

import com.htisolutions.poolref.entities.User;

public class LeaderBoardEntryViewModel {

    private Integer position;

    private Long userId;

    private String name;

    private Integer wins;

    private Integer losses;

    private Float percentage;

    public LeaderBoardEntryViewModel(User user){
        this.userId = user.getId();
        this.name = user.formatName();
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

    public Long getUserId() { return this.userId; }

    public String getName() { return this.name; }

    public Integer getWins() { return this.wins; }

    public Integer getLosses() { return this.losses; }

    public Float getPercentage() { return this.percentage; }

}
