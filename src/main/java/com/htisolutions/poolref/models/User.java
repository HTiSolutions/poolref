package com.htisolutions.poolref.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String forename;

    @NotNull
    private String surname;

    public User() { }

    public User(long id) {
        this.id = id;
    }

    public User(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
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
}


