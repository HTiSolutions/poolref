package com.htisolutions.poolref.viewModels;

import com.htisolutions.poolref.entities.User;

public class SubmitScoreViewModel {

    private Iterable<User> users;

    private User winner;

    private User loser;

    private String tweet;

    private boolean tagOpponent;

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

    public String getTweet(){return tweet;}

    public void setTweet(String tweeet){this.tweet = tweeet;}

    public boolean getTagOpponent(){return tagOpponent;}

    public void setTagOpponent(boolean tagOpponent){this.tagOpponent = tagOpponent;}

}
