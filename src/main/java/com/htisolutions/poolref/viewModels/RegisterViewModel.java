package com.htisolutions.poolref.viewModels;


public class RegisterViewModel {
    private String firstName;
    private String lastName;
    private String registerNickname;
    private String registerPassword;
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRegisterNickname() {
        return registerNickname;
    }

    public String getRegisterPassword() {
        return registerPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRegisterNickname(String registerNickname) {
        this.registerNickname = registerNickname;
    }

    public void setRegisterPassword(String registerPassword) {
        this.registerPassword = registerPassword;
    }
}
