package com.htisolutions.poolref.viewModels;

import com.htisolutions.poolref.entities.User;

public class SubmitScoreViewModel {

    private Iterable<User> users;

    private User winner;

    private User loser;

    public Iterable<User> getUsers() {
        return this.users;
    }

    public void setUsers(Iterable<User> users) {
        this.users = users;
    }

    public User getWinner() {
        return this.winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public User getLoser() {
        return this.loser;
    }

    public void setLoser(User loser) {
        this.loser = loser;
    }

}
