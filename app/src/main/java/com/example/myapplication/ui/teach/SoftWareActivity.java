package com.example.myapplication.ui.teach;

import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivitySoftwareBinding;
import com.example.myapplication.ui.base.BaseActivity;

//教程办公软件界面
public class SoftWareActivity extends BaseActivity<ActivitySoftwareBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_software;
    }

    @Override
    public void initView(View view) {
        //返回
        binding.toolBar.setNavigationOnClickListener(view1 -> {
            finish();
        });
    }
}