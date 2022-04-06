package com.example.myapplication.ui.adapter;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemCommentBinding;
import com.example.myapplication.model.forum.Comment;
import com.example.myapplication.ui.DialogUtil;

public class CommentRecyclerAdapter extends BaseRecyclerAdapter<Comment, ItemCommentBinding> {

    DialogUtil.OnPersonInfoItemClickListener onPersonInfoItemClickListener;

    public void setOnPersonInfoItemClickListener(DialogUtil.OnPersonInfoItemClickListener onPersonInfoItemClickListener) {
        this.onPersonInfoItemClickListener = onPersonInfoItemClickListener;
    }

    @Override
    int getLayoutRes() {
        return R.layout.item_comment;
    }

    @Override
    public void bindData(ItemCommentBinding binding, int position) {
        Comment comment = baseList.get(position);
        binding.setComment(comment);
        //头像
        Glide.with(binding.ivAvatar.getContext()).load(comment.getAvatar()).into(binding.ivAvatar);
    }
}
