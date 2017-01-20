package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.UserDao;
import com.htisolutions.poolref.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;

@Service
public class RegisterService {

    private UserDao userDao;

    @Autowired
    RegisterService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Boolean validRegister(String firstName, String lastName, String nickname, String registerPassword, String confirmPassword){
        if (registerPassword.equals(confirmPassword) && userDao.findByNickname(nickname) == null) {
            try {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(registerPassword);
                User user = new User(firstName, lastName, nickname, hashedPassword);
                userDao.save(user);
            }
            catch (Exception ex) {
                //"Error creating the user: " + ex.toString();
            }
            return true;
        }
        return false;
    }
}
