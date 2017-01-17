package com.htisolutions.poolref.models;

public class LeaderboardEntry{

    private Long id;

    private String nickname;

    private int wins;

    private int losses;

    private float percentage;

    public  LeaderboardEntry (Long id, String nickname){
        this.id = id;
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

    private void setPercentage(){
        percentage = wins / (wins + losses) * 100;
    }

    public Long getId() { return this.id; }

    public String getNickname() { return this.nickname; }

    public int getWins() { return this.wins; }

    public int getLosses() { return this.losses; }

    public float getPercentage() { return this.percentage; }

}
