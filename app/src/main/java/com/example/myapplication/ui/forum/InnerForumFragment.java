package com.example.myapplication.ui.forum;

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
import com.example.myapplication.ui.adapter.ForumRecyclerAdapter;
import com.example.myapplication.model.Forum;

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
        forumRecyclerAdapter = new ForumRecyclerAdapter(getContext());
        rvForum.setLayoutManager(new LinearLayoutManager(getContext()));
        rvForum.setAdapter(forumRecyclerAdapter);
        initList();
    }

    private void initList() {
        List<Forum> forumList = new ArrayList<>();
        List<String> imgList = new ArrayList<>();
        forumList.add(new Forum("", "", "", imgList));
        forumList.add(new Forum("", "", "", imgList));
        forumList.add(new Forum("", "", "", imgList));
        forumRecyclerAdapter.replaceList(forumList);
    }

}