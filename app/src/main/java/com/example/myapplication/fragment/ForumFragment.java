package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.myapplication.R;
import com.example.myapplication.adapter.TabFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ForumFragment extends Fragment {

    ViewPager vpForum;
    TabLayout tlForum;
    TabFragmentAdapter vpAdapter;
    List<Fragment> forumFragments;
    List<String> titles;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_forum, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vpForum = view.findViewById(R.id.vp_forum);
        tlForum = view.findViewById(R.id.tl_forum);
        forumFragments = new ArrayList<>();
        forumFragments.add(new InnerForumFragment("关注"));
        forumFragments.add(new InnerForumFragment("发现"));
        titles = new ArrayList<>();
        titles.add("关注");
        titles.add("发现");
        vpAdapter = new TabFragmentAdapter(getChildFragmentManager(), forumFragments,titles);
        vpForum.setAdapter(vpAdapter);
        tlForum.setupWithViewPager(vpForum);
    }
}
