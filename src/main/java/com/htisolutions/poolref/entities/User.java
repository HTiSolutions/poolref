package com.htisolutions.poolref.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String forename;

    @NotNull
    private String surname;

    @NotNull
    private String nickname;

    @NotNull
    private String hashedpassword;

    private Long twitterTokenId;

    public User() { }

    public User(Long id) {
        this.id = id;
    }

    public User(String forename, String surname, String nickname, String hashedpassword) {
        this.forename = forename;
        this.surname = surname;
        this.nickname = nickname;
        this.hashedpassword = hashedpassword;
        twitterTokenId = null;
    }

    public Long getId() {
        return this.id;
    }

    public String getForename() {
        return this.forename;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public Long getTwitterTokenId() {
        return twitterTokenId;
    }

    public String getHashedpassword() {
        return this.hashedpassword;
    }

    public void setTwitterTokenId(Long twitterTokenId){this.twitterTokenId = twitterTokenId;}


}


