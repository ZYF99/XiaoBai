package com.example.myapplication.model.rongcloud;

public class GetRongCloudTokenResultModel {
    String token;
    Long id;

    public GetRongCloudTokenResultModel(String token, Long id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
