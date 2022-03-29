package com.example.myapplication.model;

public class Message {
    String avatar;
    String content;

    public Message(String avatar, String content) {
        this.avatar = avatar;
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
