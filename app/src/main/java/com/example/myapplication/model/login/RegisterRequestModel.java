package com.example.myapplication.model.login;

public class RegisterRequestModel {
    String email;
    String password;
    String realName;
    String username;
    String verificationCode;

    public RegisterRequestModel(String email, String password, String realName, String username, String verificationCode) {
        this.email = email;
        this.password = password;
        this.realName = realName;
        this.username = username;
        this.verificationCode = verificationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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
