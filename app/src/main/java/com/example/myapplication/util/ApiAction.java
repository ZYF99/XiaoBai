package com.example.myapplication.util;

import android.widget.Toast;

import com.example.myapplication.MyApplication;

public abstract class ApiAction<T> {
        public abstract void onSuccess(T response);

        void onFailed(Throwable t) {
            Toast.makeText(MyApplication.instance, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }