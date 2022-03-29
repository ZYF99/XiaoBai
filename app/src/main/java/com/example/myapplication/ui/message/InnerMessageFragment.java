package com.example.myapplication.ui.message;

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
import com.example.myapplication.ui.adapter.MessageRecyclerAdapter;
import com.example.myapplication.model.Message;

import java.util.ArrayList;
import java.util.List;

//内嵌的论坛列表界面
public class InnerMessageFragment extends Fragment {

    String tag;
    RecyclerView rvMessage;
    MessageRecyclerAdapter messageRecyclerAdapter;

    public InnerMessageFragment() {

    }

    public InnerMessageFragment(String tag) {
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
        rvMessage = view.findViewById(R.id.rv_list);
        messageRecyclerAdapter = new MessageRecyclerAdapter(getContext());
        rvMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMessage.setAdapter(messageRecyclerAdapter);
        initList();
    }

    private void initList() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("", ""));
        messageList.add(new Message("", ""));
        messageList.add(new Message("", ""));
        messageRecyclerAdapter.replaceList(messageList);
    }

}
