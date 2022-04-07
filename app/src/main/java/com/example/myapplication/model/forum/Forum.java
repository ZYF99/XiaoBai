package com.example.myapplication.model.forum;

import com.example.myapplication.manager.RetrofitHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Forum implements Serializable {
    long id;
    String articleText;
    String filePathJson;
    List<Comment> commentList;
    String createTime;
    boolean isCollect;
    boolean isPraise;
    boolean isFollow;
    int collectNum;
    int praiseNum;

    long userId;
    String realName;
    String creatName;
    String photoPath;

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public String getCreatName() {
        return creatName;
    }

    public void setCreatName(String creatName) {
        this.creatName = creatName;
    }

    public Forum(long id, String articleText, String filePathJson, List<Comment> commentList, String createTime, boolean isCollect, boolean isPraise, boolean isFollow, int collectNum, int praiseNum, long userId, String realName, String creatName, String photoPath) {
        this.id = id;
        this.articleText = articleText;
        this.filePathJson = filePathJson;
        this.commentList = commentList;
        this.createTime = createTime;
        this.isCollect = isCollect;
        this.isPraise = isPraise;
        this.isFollow = isFollow;
        this.collectNum = collectNum;
        this.praiseNum = praiseNum;
        this.userId = userId;
        this.realName = realName;
        this.creatName = creatName;
        this.photoPath = photoPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public ArrayList<String> getImageList() {
        if (filePathJson == null || filePathJson.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<String> list = new ArrayList<>();
        String[] arr = filePathJson.replace("[", "").replace("]", "").split(",");
        for (String s : arr) {
            list.add(RetrofitHelper.BASE_FILE_URL + s);
        }
        return list;
    }

    public void setFilePathJson(String filePathJson) {
        this.filePathJson = filePathJson;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public boolean isPraise() {
        return isPraise;
    }

    public void setPraise(boolean praise) {
        isPraise = praise;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getTime() {
        if (!createTime.contains("T")) {
            return "";
        }
        return createTime.split("T")[0] + " " + createTime.split("T")[1];
    }

}
