package com.htisolutions.poolref.entities;

import javax.persistence.Entity;

/**
 * Created by root on 24/02/2017.
 */
@Entity
public class SubLeague {
    private Long id;
    private Iterable<User>usersInLeague;

    public Iterable<User> getUsersInLeague(){
        return usersInLeague;
    }
}
