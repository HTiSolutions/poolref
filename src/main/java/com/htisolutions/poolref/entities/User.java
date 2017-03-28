package com.htisolutions.poolref.entities;


import com.sun.istack.internal.Nullable;

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
    private String hashed_password;

    private Long securityQuestionId;

    private String securityQuestionAnswer;

    public User() { }

    public User(Long id) {
        this.id = id;
    }

    public User(String forename, String surname, String nickname, String hashed_password) {
        this.forename = forename;
        this.surname = surname;
        this.nickname = nickname;
        this.hashed_password = hashed_password;
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

    public String getHashedpassword() {
        return this.hashed_password;
    }

    public Long getSecurity_question_id(){return securityQuestionId;}

    public String getSecurity_question_answer(){return securityQuestionAnswer;}

    public void setSecurity_question_id(long securityQuestionId){this.securityQuestionId = securityQuestionId;}

    public void setSecurity_question_answer(String securityQuestionAnswer){this.securityQuestionAnswer = securityQuestionAnswer;}

    public void setHashed_password(String password){hashed_password = password;}
}


