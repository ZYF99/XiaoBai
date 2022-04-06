package com.example.myapplication.model.forum;

import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.Person;

public class Comment {
    long id;
    String comments;
    String creatName; //email
    String createTime;
    long forumId;
    String photoPath;
    String realName;
    long userId;

    public Comment(long id, String comments, String creatName, String createTime, long forumId, String photoPath, String realName, long userId) {
        this.id = id;
        this.comments = comments;
        this.creatName = creatName;
        this.createTime = createTime;
        this.forumId = forumId;
        this.photoPath = photoPath;
        this.realName = realName;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreatName() {
        return creatName;
    }

    public void setCreatName(String creatName) {
        this.creatName = creatName;
    }

    public String getTime() {
        if (!createTime.contains("T")) {
            return "";
        }
        return createTime.split("T")[0] + " " + createTime.split("T")[1];
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getForumId() {
        return forumId;
    }

    public void setForumId(long forumId) {
        this.forumId = forumId;
    }

    public String getAvatar() {
        return RetrofitHelper.BASE_FILE_URL+photoPath;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
