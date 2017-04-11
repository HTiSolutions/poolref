package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.SecurityQuestion;
import com.htisolutions.poolref.entities.SecurityQuestionDao;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.entities.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ResetPasswordService(UserDao userDao, SecurityQuestionDao securityQuestionDao, DaoAuthenticationProvider authenticationProvider) {
        this.userDao = userDao;
        this.securityQuestionDao = securityQuestionDao;
        this.authenticationProvider = authenticationProvider;
        user = null;
        answeredQuestion = false;
    }

    public boolean canAnswerQuestion() {
        return (user != null);
    }

    public boolean canResetPassword() {
        return (user != null && answeredQuestion);
    }

    public Boolean validName(String nickname) {
        Iterable<User> userList = userDao.findAll();
        for (User user : userList) {
            if (user.getNickname().equals(nickname)) {
                if (user.getSecurityQuestionId() != null) {
                    this.user = user;
                    return true;
                }
            }
        }
        return false;
    }

    public void autoLogin(String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getNickname(), password);
        Authentication authentication = authenticationProvider.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public boolean updatePassword(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            try {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(password);
                user.setHashedPassword(hashedPassword);
                userDao.save(user);
                return true;
            } catch (Exception ex) {
                log.error("Error updating password: {}", ex.toString());
                return false;
            }
        } else {
            return false;
        }
    }

    public User getUser(String nickname) {
        User user = userDao.findByNickname(nickname);
        return user;
    }

    public String getQuestion() {
        long questionId = user.getSecurityQuestionId();
        SecurityQuestion question = securityQuestionDao.findOne(questionId);
        return question.getQuestion();
    }

    public boolean checkSecurityAnswer(String answer) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(answer, user.getSecurityQuestionAnswer())) {
            answeredQuestion = true;
            return true;
        } else {
            return false;
        }
    }

    public void clearProgress() {
        user = null;
        answeredQuestion = false;
    }
}
