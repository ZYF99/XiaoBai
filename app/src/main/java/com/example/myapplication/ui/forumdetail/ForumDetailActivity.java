package com.example.myapplication.ui.forumdetail;

import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityForumDetailBinding;
import com.example.myapplication.ui.base.BaseActivity;

public class ForumDetailActivity extends BaseActivity<ActivityForumDetailBinding> {
    public static final String KEY_FORUM_ID = "key_forum_id";

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_forum_detail;
    }

    @Override
    public void initView(View view) {
        //退出到设置页面
        binding.toolBar.setNavigationOnClickListener(view1 -> finish());
    }

}
