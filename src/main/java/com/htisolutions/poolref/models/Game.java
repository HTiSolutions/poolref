package com.htisolutions.poolref.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="winner_id")
    private Long winnerId;

    @NotNull
    @Column(name="loser_id")
    private Long loserId;

    public Game() { }

    public Game(Long id) {
        this.id = id;
    }

    public Game(Long winnerId, Long loserId) {
        this.winnerId = winnerId;
        this.loserId = loserId;
    }

    public Long getId() {
        return this.id;
    }

    public Long getWinnerId() {
        return this.winnerId;
    }

    public Long getLoserId() {
        return this.loserId;
    }
}
