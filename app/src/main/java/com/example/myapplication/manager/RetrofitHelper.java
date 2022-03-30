package com.example.myapplication.manager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    //服务器地址
    private static final String BASE_URL = "http://119.23.49.231:9000/";
    //private static final String BASE_URL = "http://www.weather.com.cn/";

    private static RetrofitHelper instance;
    private static Retrofit retrofit;
    private Retrofit.Builder retrofitBuilder;

    private RetrofitHelper() {

    }

    //Retrofit初始化
    public void init() {
        if (retrofitBuilder == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .build();
            retrofitBuilder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //支持Json数据解析
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client);
        }
        retrofit = retrofitBuilder.build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static RetrofitHelper getInstance() {
        if (instance == null)
            instance = new RetrofitHelper();
        return instance;
    }

    public static ApiService getApiService(){
        return getRetrofit().create(ApiService.class);
    }

}
