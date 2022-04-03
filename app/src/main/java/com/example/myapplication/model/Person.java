package com.example.myapplication.model;

public class Person {
    String email;
    long id;
    String photoPath;
    String realName;

    String avatar;
    String name;

    public Person(String email, long id, String photoPath, String realName, String avatar, String name) {
        this.email = email;
        this.id = id;
        this.photoPath = photoPath;
        this.realName = realName;
        this.avatar = avatar;
        this.name = name;
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

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
