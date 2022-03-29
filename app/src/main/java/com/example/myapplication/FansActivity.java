package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.example.myapplication.adapter.TabFragmentAdapter;
import com.example.myapplication.fragment.InnerPersonFragment;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

//我的——粉丝界面
public class FansActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager vpPerson;
    TabLayout tlPerson;
    TabFragmentAdapter vpAdapter;
    List<Fragment> innerFragments;
    List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fans);
        toolbar = findViewById(R.id.tool_bar);
        //退出进入我的页面
        toolbar.setNavigationOnClickListener(view -> finish());
        vpPerson = findViewById(R.id.vp_person);
        tlPerson = findViewById(R.id.tl_person);
        innerFragments = new ArrayList<>();
        innerFragments.add(new InnerPersonFragment("粉丝"));
        innerFragments.add(new InnerPersonFragment("关注"));
        titles = new ArrayList<>();
        titles.add("粉丝");
        titles.add("关注");
        vpAdapter = new TabFragmentAdapter(getSupportFragmentManager(), innerFragments, titles);
        vpPerson.setAdapter(vpAdapter);
        tlPerson.setupWithViewPager(vpPerson);
    }
}