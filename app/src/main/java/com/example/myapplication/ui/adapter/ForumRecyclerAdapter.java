package com.example.myapplication.ui.adapter;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemForumBinding;
import com.example.myapplication.model.Forum;

public class ForumRecyclerAdapter extends BaseRecyclerAdapter<Forum, ItemForumBinding> {

    @Override
    int getLayoutRes() {
        return R.layout.item_forum;
    }

    @Override
    public void bindData(ItemForumBinding binding, int position) {

    }

}
