package com.example.myapplication.ui.modifypassword;

import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityModifyPasswordBinding;
import com.example.myapplication.databinding.ActivitySettingBinding;
import com.example.myapplication.ui.base.BaseActivity;

//设置——修改密码界面
public class ModifyPasswordActivity extends BaseActivity<ActivityModifyPasswordBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_modify_password;
    }

    @Override
    public void initView(View view) {
        //退出到设置页面
        binding.toolBar.setNavigationOnClickListener(view1 -> finish());
    }
}