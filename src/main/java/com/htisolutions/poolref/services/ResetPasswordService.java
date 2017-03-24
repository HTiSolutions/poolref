package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.SecurityQuestion;
import com.htisolutions.poolref.entities.SecurityQuestionDao;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.entities.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by garethcarless on 24/03/2017.
 */
@Service
public class ResetPasswordService {
    private UserDao userDao;
    private SecurityQuestionDao securityQuestionDao;

    @Autowired
    ResetPasswordService(UserDao userDao, SecurityQuestionDao securityQuestionDao) {
        this.userDao = userDao;
        this.securityQuestionDao = securityQuestionDao;
    }

    public Boolean validName(String nickname){
        Iterable <User> userList = userDao.findAll();
        for(User user: userList){
            if (user.getNickname().equals(nickname)){
                return true;
            }
        }
        return false;
    }

    public String getQuesetionForNickname(String nickname){
        User user = userDao.findByNickname(nickname);
        long questionId = user.getSecurity_question_id();
        SecurityQuestion question = securityQuestionDao.findOne(questionId);
        return question.getQuestion();
    }
}
