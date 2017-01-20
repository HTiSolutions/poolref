package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.UserDao;
import com.htisolutions.poolref.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;

@Service
public class LoginService {

    private UserDao userDao;

    @Autowired
    LoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Boolean validLogin(String nickname, String password){
        Iterable <User> userList = userDao.findAll();
        for(User user: userList){
            if (user.getNickname().equals(nickname)){
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
