package com.example.myapplication.model.account;

public class FetchUserInfoResultModel {
    String email;
    Long id;
    String realName;

    public FetchUserInfoResultModel(String email, Long id, String realName) {
        this.email = email;
        this.id = id;
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
