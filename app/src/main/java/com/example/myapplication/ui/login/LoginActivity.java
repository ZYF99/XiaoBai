package com.example.myapplication.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;

//登录界面
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(View view) {
        if (Hawk.get(HawkKey.KEY_HAS_LOGIN,false)) {
            MainActivity.jumpToMain(LoginActivity.this);
        }
    }


}