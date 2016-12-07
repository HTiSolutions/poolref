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

    @NotNull
    private String email;

    @NotNull
    private String hashedpassword;

    public User() { }

    public User(long id) {
        this.id = id;
    }

    public User(String forename, String surname, String email, String hashedpassword) {
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.hashedpassword = hashedpassword;
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

    public String getEmail() {
        return this.email;
    }

    public String getHashedpassword() {
        return this.hashedpassword;
    }


}


