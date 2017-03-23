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
public class SecurityQuestionService {
    public boolean validateAnswer(String answer, String confirmAnswer){
        if (answer.equals(confirmAnswer)){
            return true;
        }
        else{
            return false;
        }
    }
}
