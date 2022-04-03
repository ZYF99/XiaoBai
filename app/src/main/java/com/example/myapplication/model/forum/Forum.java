package com.example.myapplication.model.forum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Forum implements Serializable {
    long id;
    String avatar;
    String name;
    String content;
    List<String> imgList;
    long time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Forum(long id, String avatar, String name, String content, List<String> imgList, long time) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.content = content;
        this.imgList = imgList;
        this.time = time;
    }
}
