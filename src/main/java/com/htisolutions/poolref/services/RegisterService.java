package com.htisolutions.poolref.services;

import com.htisolutions.poolref.models.UserDao;
import com.htisolutions.poolref.models.User;
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

    public Boolean validRegister(String firstName, String lastName, String registerEmail, String registerPassword, String confirmPassword){
        if (registerPassword.equals(confirmPassword) && userDao.findByEmail(registerEmail) == null) {
            try {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(registerPassword);
                User user = new User(firstName, lastName, registerEmail, hashedPassword);
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
