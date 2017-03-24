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

    @Nullable
    private Long security_question_id;

    @Nullable
    private String security_question_answer;

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

    public long getSecurity_question_id(){return security_question_id;}

    public String getSecurity_question_answer(){return security_question_answer;}

    public void setSecurity_question_id(long security_question_id){this.security_question_id = security_question_id;}

    public void setSecurity_question_answer(String security_question_answer){this.security_question_answer = security_question_answer;}


}


