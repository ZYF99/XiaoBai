package com.example.myapplication.model.forum;

import com.example.myapplication.model.Person;

public class Comment {
    Person person;
    String content;
    long time;

    public Comment(Person person, String content, long time) {
        this.person = person;
        this.content = content;
        this.time = time;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
