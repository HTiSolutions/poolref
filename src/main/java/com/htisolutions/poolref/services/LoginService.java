package com.htisolutions.poolref.services;

import com.htisolutions.poolref.models.User;
import com.htisolutions.poolref.models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Service
public class LoginService {

    private UserDao userDao;

    @Autowired
    LoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Boolean validLogin(String email, String password){
        Iterable <User> userList = userDao.findAll();
        for(User user: userList){
            if (user.getEmail().equals(email)){
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = user.getHashedpassword();
                if (passwordEncoder.matches(password,hashedPassword)){
                    return true;
                }
            }
        }
        return false;
    }

}
