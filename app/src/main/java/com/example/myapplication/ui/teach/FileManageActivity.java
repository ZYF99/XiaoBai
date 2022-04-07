package com.example.myapplication.ui.teach;

import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityFileManageBinding;
import com.example.myapplication.ui.base.BaseActivity;

//教程文件管理界面
public class FileManageActivity extends BaseActivity<ActivityFileManageBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_file_manage;
    }

    @Override
    public void initView(View view) {
        //返回
        binding.toolBar.setNavigationOnClickListener(view1 -> {
            finish();
        });
    }
}