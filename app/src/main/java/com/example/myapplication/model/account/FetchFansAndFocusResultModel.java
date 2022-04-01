package com.example.myapplication.model.account;

import com.example.myapplication.model.Person;

import java.util.List;

public class FetchFansAndFocusResultModel {
    List<Person> follows;
    List<Person> fans;

    public FetchFansAndFocusResultModel(List<Person> follows, List<Person> fans) {
        this.follows = follows;
        this.fans = fans;
    }

    public List<Person> getFollows() {
        return follows;
    }

    public void setFollows(List<Person> follows) {
        this.follows = follows;
    }

    public List<Person> getFans() {
        return fans;
    }

    public void setFans(List<Person> fans) {
        this.fans = fans;
    }
}
