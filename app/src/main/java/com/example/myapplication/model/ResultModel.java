package com.example.myapplication.model;

public class ResultModel<T> {
    int code;
    T data;
    String msg;
    Boolean state;

    public ResultModel(int code, T data, String msg, Boolean state) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        if (msg == null)
            return "";
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
