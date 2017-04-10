package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.SecurityQuestion;
import com.htisolutions.poolref.entities.SecurityQuestionDao;
import com.htisolutions.poolref.entities.UserDao;
import com.htisolutions.poolref.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;

@Service
public class SecurityQuestionService {

    private SecurityQuestionDao securityQuestionDao;
    private UserDao userDao;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

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
                user.setSecurityQuestionId(questionId);
                user.setSecurityQuestionAnswer(hashedAnswer);
                userDao.save(user);

            }
            catch (Exception ex) {
                log.error("Error updating question: {}", ex.toString());
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
