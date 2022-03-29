package com.example.myapplication.ui.secret;

import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityModifyPasswordBinding;
import com.example.myapplication.databinding.ActivitySecretBinding;
import com.example.myapplication.ui.base.BaseActivity;

//设置——隐私设置界面
public class SecretActivity extends BaseActivity<ActivitySecretBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_secret;
    }

    @Override
    public void initView(View view) {
        //退出到设置页面
        binding.toolBar.setNavigationOnClickListener(view1 -> finish());
    }
}