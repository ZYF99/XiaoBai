package com.example.myapplication.ui.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.ui.adapter.TabFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {

    ViewPager vpMessage;
    TabLayout tlMessage;
    TabFragmentAdapter vpAdapter;
    List<Fragment> messageFragments;
    List<String> titles;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_message, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vpMessage = view.findViewById(R.id.vp_message);
        tlMessage = view.findViewById(R.id.tl_message);
        messageFragments = new ArrayList<>();
        messageFragments.add(new InnerMessageFragment("赞和收藏"));
        messageFragments.add(new InnerMessageFragment("新增关注"));
        messageFragments.add(new InnerMessageFragment("评论"));
        titles = new ArrayList<>();
        titles.add("赞和收藏");
        titles.add("新增关注");
        titles.add("评论");
        vpAdapter = new TabFragmentAdapter(getChildFragmentManager(), messageFragments,titles);
        vpMessage.setAdapter(vpAdapter);
        tlMessage.setupWithViewPager(vpMessage);
    }
}
