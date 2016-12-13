package com.htisolutions.poolref.services;

import com.htisolutions.poolref.models.UserDao;
import java.util.ArrayList;
import com.htisolutions.poolref.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Iterable<User> getUsers(){
        Iterable<User> users = userDao.findAll();
        return users;
    }

    public Iterable<String> getUserEmails(){
        Iterable<User> users = getUsers();
        ArrayList<String> userEmailList= new ArrayList<String>();

        for (User u : users) {
            userEmailList.add(u.getEmail());
        }
        Iterable<String> userEmails = userEmailList;
        return userEmails;
    }

    public User getUserById(long id) {
        User user = userDao.findOne(id);
        return user;
    }

}
