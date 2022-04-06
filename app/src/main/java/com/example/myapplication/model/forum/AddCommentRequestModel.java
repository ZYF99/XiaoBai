package com.example.myapplication.model.forum;

public class AddCommentRequestModel {
    String comments;
    long forumId;

    public AddCommentRequestModel(String comments, long forumId) {
        this.comments = comments;
        this.forumId = forumId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public long getForumId() {
        return forumId;
    }

    public void setForumId(long forumId) {
        this.forumId = forumId;
    }
}
