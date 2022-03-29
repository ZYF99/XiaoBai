package com.example.myapplication.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Person;

import java.util.List;

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.ForumViewHolder> {
    Context context;
    List<Person> mList;

    public PersonRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void replaceList(List<Person> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public ForumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_person, parent, false);
        return new ForumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ForumViewHolder extends RecyclerView.ViewHolder {

        public ForumViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
