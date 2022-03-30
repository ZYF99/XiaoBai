package com.example.myapplication.ui.forum;

import static com.example.myapplication.ui.forumdetail.ForumDetailActivity.KEY_FORUM_ID;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemForumBinding;
import com.example.myapplication.ui.adapter.BaseRecyclerAdapter;
import com.example.myapplication.ui.adapter.ForumRecyclerAdapter;
import com.example.myapplication.model.Forum;
import com.example.myapplication.ui.forumdetail.ForumDetailActivity;

import java.util.ArrayList;
import java.util.List;

//内嵌的论坛列表界面
public class InnerForumFragment extends Fragment {

    String tag;
    RecyclerView rvForum;
    ForumRecyclerAdapter forumRecyclerAdapter;

    public InnerForumFragment() {

    }

    public InnerForumFragment(String tag) {
        this.tag = tag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_inner_list, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvForum = view.findViewById(R.id.rv_list);
        forumRecyclerAdapter = new ForumRecyclerAdapter();
        rvForum.setLayoutManager(new LinearLayoutManager(getContext()));
        rvForum.setAdapter(forumRecyclerAdapter);
        forumRecyclerAdapter.setOnCellClickListener((itemForumBinding, forum) -> {
            jumpToForumDetail(getContext(),forum.getId());
        });
        initList();
    }

    private void initList() {
        List<Forum> forumList = new ArrayList<>();
        List<String> imgList = new ArrayList<>();
        forumList.add(new Forum("", "", "", imgList));
        forumList.add(new Forum("", "", "", imgList));
        forumList.add(new Forum("", "", "", imgList));
        forumRecyclerAdapter.replaceData(forumList);
    }

    private void jumpToForumDetail(Context context,int forumId){
        Intent intent = new Intent(context, ForumDetailActivity.class);
        intent.putExtra(KEY_FORUM_ID, forumId);
        context.startActivity(intent);
    }

}
