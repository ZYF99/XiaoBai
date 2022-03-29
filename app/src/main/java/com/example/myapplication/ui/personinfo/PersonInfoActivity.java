package com.example.myapplication.ui.personinfo;

import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityModifyPasswordBinding;
import com.example.myapplication.databinding.ActivityPersonInfoBinding;
import com.example.myapplication.databinding.ActivitySecretBinding;
import com.example.myapplication.ui.base.BaseActivity;

//设置——个人资料界面
public class PersonInfoActivity extends BaseActivity<ActivityPersonInfoBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_person_info;
    }

    @Override
    public void initView(View view) {
        //退出到设置页面
        binding.toolBar.setNavigationOnClickListener(view1 -> finish());
    }
}