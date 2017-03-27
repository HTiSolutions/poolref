package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.SecurityQuestion;
import com.htisolutions.poolref.entities.SecurityQuestionDao;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.entities.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ResetPasswordService {
    private UserDao userDao;
    private SecurityQuestionDao securityQuestionDao;
    private DaoAuthenticationProvider authenticationProvider;
    private User user;
    private boolean answeredQuestion;

    @Autowired
    ResetPasswordService(UserDao userDao, SecurityQuestionDao securityQuestionDao, DaoAuthenticationProvider authenticationProvider) {
        this.userDao = userDao;
        this.securityQuestionDao = securityQuestionDao;
        this.authenticationProvider = authenticationProvider;
        user = null;
        answeredQuestion = false;
    }

    public boolean canAnswerQuestion(){
        return (user != null);
    }
    public boolean canResetPassword(){
        return (user != null && answeredQuestion);
    }
    public Boolean validName(String nickname){
        Iterable <User> userList = userDao.findAll();
        for(User user: userList){
            if (user.getNickname().equals(nickname)){
                if(user.getSecurity_question_id() != null) {
                    this.user = user;
                    return true;
                }
            }
        }
        return false;
    }
    public void autologin(String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getNickname(), password);
        Authentication authentication = authenticationProvider.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication );
    }
    public boolean validPassword(String password, String confirm){
        if (password.equals(confirm)){
            try {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(password);
                user.setHashed_password(hashedPassword);
                userDao.save(user);
                return true;
            }
            catch (Exception ex) {
                return false;
            }
        }
        else{
            return false;
        }
    }

    public User getUser(String nickname){
        User user = userDao.findByNickname(nickname);
        return user;
    }

    public String getQuesetion(){
        long questionId = user.getSecurity_question_id();
        SecurityQuestion question = securityQuestionDao.findOne(questionId);
        return question.getQuestion();
    }

    public boolean checkSecurityAnswer(String answer){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(answer, user.getSecurity_question_answer())){
            answeredQuestion = true;
            return true;
        }
        else{
            return false;
        }
    }

    public void clearProgress(){
        user = null;
        answeredQuestion = false;
    }
}
