package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.UserDao;
import com.htisolutions.poolref.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;

@Service
public class RegisterService {

    private UserDao userDao;
    private DaoAuthenticationProvider authenticationProvider;

    @Autowired
    RegisterService(UserDao userDao, DaoAuthenticationProvider authenticationProvider) {
        this.userDao = userDao;
        this.authenticationProvider = authenticationProvider;
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

    public void autologin(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationProvider.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication );
        //request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }
}
