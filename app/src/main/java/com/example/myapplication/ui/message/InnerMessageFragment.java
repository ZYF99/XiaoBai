package com.example.myapplication.ui.message;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentInnerListBinding;
import com.example.myapplication.ui.adapter.MessageRecyclerAdapter;
import com.example.myapplication.model.Message;
import com.example.myapplication.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

//内嵌的消息列表界面
public class InnerMessageFragment extends BaseFragment<FragmentInnerListBinding> {

    String tag;
    MessageRecyclerAdapter messageRecyclerAdapter;

    public InnerMessageFragment() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_inner_list;
    }

    public InnerMessageFragment(String tag) {
        this.tag = tag;
    }

    @Override
    public void initView(View view) {
        messageRecyclerAdapter = new MessageRecyclerAdapter();
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvList.setAdapter(messageRecyclerAdapter);
        initList();
    }

    private void initList() {
        List<Message> messageList = new ArrayList<>();
        switch (tag) {
            case "赞和收藏":
                messageList.add(new Message("收到一个新的点赞～", "系统消息"));
                messageList.add(new Message("你的论坛被别人收藏啦～", "系统消息"));
                messageList.add(new Message("收到一个新的点赞～", "系统消息"));
                messageList.add(new Message("你的论坛被别人收藏啦～", "系统消息"));
                messageList.add(new Message("你的论坛被别人收藏啦～", "系统消息"));
                break;
            case "新增关注":
                messageList.add(new Message("有一个人默默关注你啦～", "系统消息"));
                messageList.add(new Message("有一个人默默关注你啦～", "系统消息"));
                messageList.add(new Message("有一个人默默关注你啦～", "系统消息"));
                break;
            case "评论":
                messageList.add(new Message("收获一个新的评论～", "系统消息"));
                messageList.add(new Message("收获一个新的评论～", "系统消息"));
                messageList.add(new Message("收获一个新的评论～", "系统消息"));
                break;
            default:
                break;
        }
        messageRecyclerAdapter.replaceData(messageList);
    }

}
