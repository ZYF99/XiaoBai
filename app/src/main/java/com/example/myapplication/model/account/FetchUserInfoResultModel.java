package com.example.myapplication.model.account;

import com.example.myapplication.manager.RetrofitHelper;

public class FetchUserInfoResultModel {
    String email;
    Long id;
    String realName;
    String photoPath;
    boolean isComment;

    public FetchUserInfoResultModel(String email, Long id, String realName, String photoPath, boolean isComment) {
        this.email = email;
        this.id = id;
        this.realName = realName;
        this.photoPath = photoPath;
        this.isComment = isComment;
    }

    public boolean isComment() {
        return isComment;
    }

    public void setComment(boolean comment) {
        isComment = comment;
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

    public String getAvatar() {
        return RetrofitHelper.BASE_FILE_URL + photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
