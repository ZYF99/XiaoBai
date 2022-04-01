package com.example.myapplication.manager;

import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                //.header("Connection", "close")
                .header("Authorization", Hawk.get(HawkKey.KEY_TOKEN, ""))
                .build();
        return chain.proceed(request);
    }
}


