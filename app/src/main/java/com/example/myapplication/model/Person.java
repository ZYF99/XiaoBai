package com.example.myapplication.model;

public class Person {
    String email;
    long id;
    String photoPath;
    String realName;

    public Person(String email, long id, String photoPath, String realName) {
        this.email = email;
        this.id = id;
        this.photoPath = photoPath;
        this.realName = realName;
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
}
