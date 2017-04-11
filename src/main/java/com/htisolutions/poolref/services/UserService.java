package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.UserDao;
import java.util.ArrayList;
import java.util.Comparator;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.viewModels.LeaderBoardEntryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.*;
import java.util.*;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Iterable<User> getUsers(){
        Iterable<User> users = userDao.findAll();
        List<User> listOfUsers = new ArrayList<>();
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for(User user : users){
            if (!user.getId().equals(currentUser.getId())) {
                listOfUsers.add(user);
            }
        }

        Comparator<User> userComparator = Comparator
                .comparing((User e)-> e.getSurname().toUpperCase());

        Collections.sort(listOfUsers, userComparator);
        listOfUsers.add(0, currentUser);
        return listOfUsers;
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

    public User getUserByNickname(String name) {
      User user = userDao.findByNickname(name);
      return user;
    }
}
