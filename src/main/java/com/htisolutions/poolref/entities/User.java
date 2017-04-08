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
    private String hashedPassword;

    private Long securityQuestionId;

    private String securityQuestionAnswer;

    private Long twitterTokenId;

    public User() { }

    public User(Long id) {
        this.id = id;
    }

    public User(String forename, String surname, String nickname, String hashedPassword) {
        this.forename = forename;
        this.surname = surname;
        this.nickname = nickname;
        this.hashedPassword = hashedPassword;
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
        return this.hashedPassword;
    }

    public Long getSecurityQuestionId(){return securityQuestionId;}
    public void setTwitterTokenId(Long twitterTokenId){this.twitterTokenId = twitterTokenId;}


    public String getSecurityQuestionAnswer(){return securityQuestionAnswer;}

    public void setSecurityQuestionId(long securityQuestionId){this.securityQuestionId = securityQuestionId;}

    public void setSecurityQuestionAnswer(String securityQuestionAnswer){this.securityQuestionAnswer = securityQuestionAnswer;}

    public void setHashedPassword(String password){hashedPassword = password;}
}


