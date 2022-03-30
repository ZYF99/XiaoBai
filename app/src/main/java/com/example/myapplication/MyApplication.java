package com.example.myapplication;

import android.app.Application;
import com.example.myapplication.manager.RetrofitHelper;

import io.rong.imkit.RongIM;

public class MyApplication extends Application {

    public static MyApplication instance;
    public static Boolean rongCloudConnected;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RetrofitHelper.getInstance().init();
        initRongCloud();
    }

    private void initRongCloud(){
        // 已为您替换为开发环境的 App Key
        String appKey = "pvxdm17jpesvr";
        RongIM.init(MyApplication.instance, appKey);
    }

}
