package com.example.myapplication.model.login;

public class LoginResultModel {
    String token;

    public LoginResultModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
