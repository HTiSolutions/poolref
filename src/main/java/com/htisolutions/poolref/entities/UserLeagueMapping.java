package com.htisolutions.poolref.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class UserLeagueMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="league_id")
    private Long leagueId;

    @NotNull
    @Column(name="user_id")
    private Long userId;

    public UserLeagueMapping() { }

    public UserLeagueMapping(Long id) {
        this.id = id;
    }

    public UserLeagueMapping(Long leagueId, Long userId) {
        this.leagueId = leagueId;
        this.userId = userId;
    }

    public Long getId() {
        return this.id;
    }

    public Long getLeagueId() {
        return this.leagueId;
    }

    public Long getUserId() {
        return this.userId;
    }

}
