package com.example.myapplication.ui.adapter;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemCommentBinding;
import com.example.myapplication.model.Person;
import com.example.myapplication.model.forum.Comment;
import com.example.myapplication.ui.DateUtil;
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
        binding.tvTime.setText(DateUtil.formatDate(comment.getTime()));
        binding.ivAvatar.setOnClickListener(view -> DialogUtil.showPersonInfoDialog(
                binding.ivAvatar.getContext(),
                new Person("2131423@qq.com", 959393354200645632l, comment.getPerson().getPhotoPath(), "XXX"),
                onPersonInfoItemClickListener
        ));
    }
}
