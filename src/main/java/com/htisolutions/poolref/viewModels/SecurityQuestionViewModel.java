package com.htisolutions.poolref.viewModels;

/**
 * Created by root on 23/03/2017.
 */
public class SecurityQuestionViewModel {
    private String securityQuestion;
    private String answer;
    private String confirmAnswer;

    public String getSecurityQuestion(){return securityQuestion;}

    public String getAnswer(){return answer;}

    public String getConfirmAnswer(){return confirmAnswer;}

    public void setSecurityQuestion(String securityQuestion){this.securityQuestion = securityQuestion;}

    public void setAnswer(String answer){this.answer = answer;}

    public void setConfirmAnswer(String confirmAnswer){this.confirmAnswer = confirmAnswer;}
}
