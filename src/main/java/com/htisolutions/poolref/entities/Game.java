package com.htisolutions.poolref.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "date")
    private Date date;

    @NotNull
    @Column(name = "winner_id")
    private Long winnerId;

    @NotNull
    @Column(name = "loser_id")
    private Long loserId;

    @Column(name = "game_data")
    private String gameData;

    public Game() {
    }

    public Game(Long id) {
        this.id = id;
    }

    public Game(Date date, Long winnerId, Long loserId) {
        this.date = date;
        this.winnerId = winnerId;
        this.loserId = loserId;
    }

    public Long getId() {
        return this.id;
    }

    public Date getDate() {
        return this.date;
    }

    public Long getWinnerId() {
        return this.winnerId;
    }

    public Long getLoserId() {
        return this.loserId;
    }

    public String getGameData() {
        return this.gameData;
    }

    public void setGameData(String gameData) {
        this.gameData = gameData;
    }

}

