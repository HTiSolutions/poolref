package com.htisolutions.poolref.models;


import com.htisolutions.poolref.entities.League;
import com.htisolutions.poolref.entities.User;

import java.util.List;

public class LeagueModel {

    private League league;

    private Iterable<User> users;

    public LeagueModel(League league, Iterable<User> users) {
        this.league = league;
        this.users = users;
    }

    public League getLeague() {
        return this.league;
    }

    public Iterable<User> getUsers() { return this.users; }

    public void setUsers(List<User> users) { this.users = users; }

}
