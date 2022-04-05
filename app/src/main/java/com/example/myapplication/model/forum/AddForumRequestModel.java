package com.example.myapplication.model.forum;

public class AddForumRequestModel {
    String articleText;
    String filePathJson;

    public AddForumRequestModel(String articleText, String filePathJson) {
        this.articleText = articleText;
        this.filePathJson = filePathJson;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public String getFilePathJson() {
        return filePathJson;
    }

    public void setFilePathJson(String filePathJson) {
        this.filePathJson = filePathJson;
    }
}
