package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.SecurityQuestion;
import com.htisolutions.poolref.entities.SecurityQuestionDao;
import com.htisolutions.poolref.entities.UserDao;
import com.htisolutions.poolref.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SecurityQuestionService {

    private SecurityQuestionDao securityQuestionDao;
    private UserDao userDao;

    @Autowired
    SecurityQuestionService(SecurityQuestionDao securityQuestionDao, UserDao userDao) {
        this.securityQuestionDao = securityQuestionDao;
        this.userDao = userDao;
    }

    public boolean validateQuestion(String answer, String confirmAnswer, long questionId){
        if (answer.equals(confirmAnswer)){
            try {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedAnswer= passwordEncoder.encode(answer);
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                user.setSecurity_question_id(questionId);
                user.setSecurity_question_answer(hashedAnswer);
                userDao.save(user);

            }
            catch (Exception ex) {
                //"Error creating the user: " + ex.toString();
            }
            return true;
        }
        else{
            return false;
        }
    }

    public Iterable<SecurityQuestion> getOptions(){
        return securityQuestionDao.findAll();
    }
}
