package com.htisolutions.poolref.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="name")
    private String name;

    public League() { }

    public League(Long id) {
        this.id = id;
    }

    public League(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
