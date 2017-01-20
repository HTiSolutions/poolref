package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.UserDao;
import java.util.ArrayList;
import com.htisolutions.poolref.entities.User;
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

    public Iterable<String> getUserNicknames(){
        Iterable<User> users = getUsers();
        ArrayList<String> userNicknameList= new ArrayList<String>();

        for (User u : users) {
            userNicknameList.add(u.getNickname());
        }
        Iterable<String> userNicknames = userNicknameList;
        return userNicknames;
    }

    public User getUserById(long id) {
        User user = userDao.findOne(id);
        return user;
    }

}
