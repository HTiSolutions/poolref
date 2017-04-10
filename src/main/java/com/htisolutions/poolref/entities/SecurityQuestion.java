package com.htisolutions.poolref.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class SecurityQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="question")
    private String question;

    public SecurityQuestion() { }

    public SecurityQuestion(Long id) {
        this.id = id;
    }

    public SecurityQuestion(String question) {
        this.question = question;
    }

    public Long getId() {
        return this.id;
    }

    public String getQuestion() {
        return this.question;
    }

}
