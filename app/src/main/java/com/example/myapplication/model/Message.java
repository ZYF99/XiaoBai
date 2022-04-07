package com.example.myapplication.model;

import com.example.myapplication.model.forum.Comment;
import com.example.myapplication.model.forum.Forum;

public class Message {
    long id;
    String type;
    Forum forum;
    Comment comment;

    public Message(long id, String type, Forum forum, Comment comment) {
        this.id = id;
        this.type = type;
        this.forum = forum;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        switch (type) {
            case "PRAISE":
            case "COLLECTION":
            case "COMMENT":
                return "点击查看详情";
            case "FOLLOW":
                return "快去粉丝列表查看吧";
            default:
                return "";
        }
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        switch (type) {
            case "FOLLOW":
                return "有新的关注啦～";
            case "PRAISE":
                return "有新的点赞啦～";
            case "COLLECTION":
                return "你的笔记被收藏啦～";
            case "COMMENT":
                return "你有新的留言啦～";
            default:
                return "";
        }
    }

    public void setType(String type) {
        this.type = type;
    }
}
