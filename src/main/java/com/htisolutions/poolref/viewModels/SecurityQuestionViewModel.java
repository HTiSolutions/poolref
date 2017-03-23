package com.htisolutions.poolref.viewModels;

import com.htisolutions.poolref.entities.SecurityQuestion;

import java.util.List;

/**
 * Created by root on 23/03/2017.
 */
public class SecurityQuestionViewModel {
    private String answer;
    private String confirmAnswer;
    private Iterable<SecurityQuestion> securityQuestions;
    private long questionId;

    public Iterable <SecurityQuestion> getSecurityQuestion(){return securityQuestions;}

    public long getQuestionId(){return questionId;}

    public String getAnswer(){return answer;}

    public String getConfirmAnswer(){return confirmAnswer;}

    public void setSecurityQuestions(Iterable <SecurityQuestion> securityQuestions){this.securityQuestions = securityQuestions;}

    public void setAnswer(String answer){this.answer = answer;}

    public void setQuestionId(long questionId){this.questionId = questionId;}

    public void setConfirmAnswer(String confirmAnswer){this.confirmAnswer = confirmAnswer;}
}
