package com.example.myapplication.ui.person;

import androidx.fragment.app.Fragment;
import android.view.View;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityFansBinding;
import com.example.myapplication.ui.adapter.TabFragmentAdapter;
import com.example.myapplication.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;

//我的——粉丝界面
public class FansActivity extends BaseActivity<ActivityFansBinding> {

    TabFragmentAdapter vpAdapter;
    List<Fragment> innerFragments;
    List<String> titles;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_fans;
    }

    @Override
    public void initView(View view) {
        //退出进入我的页面
        binding.toolBar.setNavigationOnClickListener(view2 -> finish());

        innerFragments = new ArrayList<>();
        innerFragments.add(new InnerPersonFragment("粉丝"));
        innerFragments.add(new InnerPersonFragment("关注"));
        titles = new ArrayList<>();
        titles.add("粉丝");
        titles.add("关注");
        vpAdapter = new TabFragmentAdapter(getSupportFragmentManager(), innerFragments, titles);
        binding.vpPerson.setAdapter(vpAdapter);
        binding.tlPerson.setupWithViewPager(binding.vpPerson);
    }
}