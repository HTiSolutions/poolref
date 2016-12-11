package com.htisolutions.poolref.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String winner;

    @NotNull
    private String loser;

    public Game() { }

    public Game(long id) {
        this.id = id;
    }

    public Game(String winner, String loser) {
        this.winner = winner;
        this.loser = loser;
    }

    public Long getId() {
        return this.id;
    }

    public String getWinner() {
        return this.winner;
    }

    public String getLoser() {
        return this.loser;
    }
}
