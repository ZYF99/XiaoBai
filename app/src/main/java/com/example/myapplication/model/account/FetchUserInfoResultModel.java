package com.example.myapplication.model.account;

public class FetchUserInfoResultModel {
    String email;
    Long id;
    String realName;
    String photoPath;

    public FetchUserInfoResultModel(String email, Long id, String realName, String photoPath) {
        this.email = email;
        this.id = id;
        this.realName = realName;
        this.photoPath = photoPath;
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
