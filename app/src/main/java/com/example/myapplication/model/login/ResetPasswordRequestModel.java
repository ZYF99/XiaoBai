package com.example.myapplication.model.login;

public class ResetPasswordRequestModel {
    String password;
    String username;
    String verificationCode;

    public ResetPasswordRequestModel(String password, String username, String verificationCode) {
        this.password = password;
        this.username = username;
        this.verificationCode = verificationCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
