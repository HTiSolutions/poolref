package com.htisolutions.poolref.viewModels;

import com.htisolutions.poolref.entities.User;

import java.util.List;

public class CreateSubLeagueViewModel {

    	private Iterable<User> users;

			public Iterable<User> getUsers() {
					return this.users;
			}
			
			public void setUsers(Iterable<User> users) {
	        this.users = users;
	    }
}
