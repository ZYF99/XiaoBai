package com.example.myapplication.ui.message;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentMessageBinding;
import com.example.myapplication.ui.adapter.TabFragmentAdapter;
import com.example.myapplication.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.utils.RouteUtils;

public class MessageFragment extends BaseFragment<FragmentMessageBinding> {

    TabFragmentAdapter vpAdapter;
    List<Fragment> messageFragments;
    List<String> titles;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView(View view) {
        messageFragments = new ArrayList<>();
        messageFragments.add(new InnerMessageFragment("赞和收藏"));
        messageFragments.add(new InnerMessageFragment("新增关注"));
        messageFragments.add(new InnerMessageFragment("评论"));
        titles = new ArrayList<>();
        titles.add("赞和收藏");
        titles.add("新增关注");
        titles.add("评论");
        vpAdapter = new TabFragmentAdapter(getChildFragmentManager(), messageFragments, titles);
        binding.vpMessage.setAdapter(vpAdapter);
        binding.tlMessage.setupWithViewPager(binding.vpMessage);
        binding.ivMessage.setOnClickListener(view1 -> openIM());
    }


    private void openIM() {
        //跳转到默认会话列表页。
        RouteUtils.routeToConversationListActivity(getContext(), "");
    }

}
