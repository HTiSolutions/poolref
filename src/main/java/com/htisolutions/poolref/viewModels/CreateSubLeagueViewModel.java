package com.htisolutions.poolref.viewModels;

import com.htisolutions.poolref.entities.User;

import java.util.List;
import java.util.ArrayList;

public class CreateSubLeagueViewModel {


    private Iterable<User> users;
    private Iterable<User> players;
    private Iterable<String> leagues;
    private String player;
    private String name;
    private long id;


    public Iterable<User> getUsers() {
        return this.users;
    }

    public void setAllUsers(Iterable<User> users) {
        this.users = users;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPlayers(Iterable<User> players) {
        this.players = players;
    }

    public Iterable<User> getPlayers() {
        return this.players;
    }

    public void setPlayer(String name) {
        this.player = name;
    }

    public String getPlayer(){
        return this.player;
    }

    public void setLeagues(Iterable<String> leagues) {
        this.leagues = leagues;
    }

    public Iterable<String> getLeagues() {
        return this.leagues;
    }
}
