package com.example.myapplication.model;

import com.example.myapplication.manager.RetrofitHelper;

public class Person {
    String email;
    long id;
    String photoPath;
    String realName;
    boolean isFollow;

    public Person(String email, long id, String photoPath, String realName, boolean isFollow) {
        this.email = email;
        this.id = id;
        this.photoPath = photoPath;
        this.realName = realName;
        this.isFollow = isFollow;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getAvatar() {
        return RetrofitHelper.BASE_FILE_URL + photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
