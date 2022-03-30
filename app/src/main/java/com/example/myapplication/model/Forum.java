package com.example.myapplication.model;

import java.io.Serializable;
import java.util.List;

public class Forum implements Serializable {
    int id;
    String avatar;
    String name;
    String content;
    List<String> imgList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Forum(int id, String avatar, String name, String content, List<String> imgList) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.content = content;
        this.imgList = imgList;
    }

    public Forum(String avatar, String name, String content, List<String> imgList) {
        this.id = 26347;
        this.avatar = avatar;
        this.name = name;
        this.content = content;
        this.imgList = imgList;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
