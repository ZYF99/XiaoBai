package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.myapplication.FansActivity;
import com.example.myapplication.SettingActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.TabFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class MineFragment extends Fragment {

    ViewPager vpForum;
    TabLayout tlForum;
    TabFragmentAdapter vpAdapter;
    List<Fragment> forumFragments;
    List<String> titles;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //跳转进入设置页面
        TextView tvSetting = view.findViewById((R.id.tv_setting));
        tvSetting.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), SettingActivity.class);
            startActivity(intent);
        });

        //跳转进入粉丝页面
        view.findViewById(R.id.tv_fans).setOnClickListener(view12 -> {
            Intent intent = new Intent(getContext(), FansActivity.class);
            startActivity(intent);
        });

        vpForum = view.findViewById(R.id.vp_forum);
        tlForum = view.findViewById(R.id.tl_forum);
        forumFragments = new ArrayList<>();
        forumFragments.add(new InnerForumFragment("笔记"));
        forumFragments.add(new InnerForumFragment("收藏"));
        forumFragments.add(new InnerForumFragment("赞过"));
        titles = new ArrayList<>();
        titles.add("笔记");
        titles.add("收藏");
        titles.add("赞过");
        vpAdapter = new TabFragmentAdapter(getChildFragmentManager(), forumFragments, titles);
        vpForum.setAdapter(vpAdapter);
        tlForum.setupWithViewPager(vpForum);
    }
}
