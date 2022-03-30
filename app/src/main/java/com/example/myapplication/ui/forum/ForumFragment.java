package com.example.myapplication.ui.forum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentForumBinding;
import com.example.myapplication.ui.adapter.TabFragmentAdapter;
import com.example.myapplication.ui.addforum.AddForumActivity;
import com.example.myapplication.ui.base.BaseFragment;
import java.util.ArrayList;
import java.util.List;

public class ForumFragment extends BaseFragment<FragmentForumBinding> {

    TabFragmentAdapter vpAdapter;
    List<Fragment> forumFragments;
    List<String> titles;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_forum;
    }


    @Override
    public void initView(View view) {
        forumFragments = new ArrayList<>();
        forumFragments.add(new InnerForumFragment("关注"));
        forumFragments.add(new InnerForumFragment("发现"));
        titles = new ArrayList<>();
        titles.add("关注");
        titles.add("发现");
        vpAdapter = new TabFragmentAdapter(getChildFragmentManager(), forumFragments,titles);
        binding.vpForum.setAdapter(vpAdapter);
        binding.tlForum.setupWithViewPager(binding.vpForum);
        binding.ivAddForum.setOnClickListener(view1 -> {
            jumpToAddForum(getContext());
        });
    }

    private void jumpToAddForum(Context context){
        Intent intent = new Intent(context, AddForumActivity.class);
        context.startActivity(intent);
    }

}
