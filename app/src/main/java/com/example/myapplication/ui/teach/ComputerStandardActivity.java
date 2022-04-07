package com.example.myapplication.ui.teach;

import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityComputerStandardBinding;
import com.example.myapplication.ui.base.BaseActivity;

//教程电脑基础界面
public class ComputerStandardActivity extends BaseActivity<ActivityComputerStandardBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_computer_standard;
    }

    @Override
    public void initView(View view) {
        //返回
        binding.toolBar.setNavigationOnClickListener(view1 -> {
            finish();
        });
    }
}