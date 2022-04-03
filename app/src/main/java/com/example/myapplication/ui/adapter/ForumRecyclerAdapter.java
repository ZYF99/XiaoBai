package com.example.myapplication.ui.adapter;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemForumBinding;
import com.example.myapplication.model.forum.Forum;
import com.example.myapplication.model.Person;
import com.example.myapplication.ui.DateUtil;
import com.example.myapplication.ui.DialogUtil;

import java.util.ArrayList;

import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;

public class ForumRecyclerAdapter extends BaseRecyclerAdapter<Forum, ItemForumBinding> {

    BGASortableNinePhotoLayout.Delegate delegate;
    DialogUtil.OnPersonInfoItemClickListener onPersonInfoItemClickListener;
    OnBottomFunctionClickListener onBottomFunctionClickListener;

    public interface OnBottomFunctionClickListener {
        public void onLikeClick(Forum forum);

        public void onCollectionClick(Forum forum);

        public void onCommentClick(Forum forum);
    }

    public ForumRecyclerAdapter(BGASortableNinePhotoLayout.Delegate delegate) {
        this.delegate = delegate;
    }

    @Override
    int getLayoutRes() {
        return R.layout.item_forum;
    }

    @Override
    public void bindData(ItemForumBinding binding, int position) {
        Forum forum = baseList.get(position);
        binding.setForum(forum);

        //头像
        Glide.with(binding.ivAvatar.getContext()).load(forum.getAvatar()).into(binding.ivAvatar);

        //时间
        binding.tvTime.setText(DateUtil.formatDate(forum.getTime()));

        //九宫格图片
        binding.rvImg.setData((ArrayList<String>) forum.getImgList());
        binding.rvImg.setDelegate(delegate);

        binding.ivAvatar.setOnClickListener(view -> DialogUtil.showPersonInfoDialog(
                binding.ivAvatar.getContext(),
                new Person("2131423@qq.com", 959393354200645632l, forum.getAvatar(), "XXX"),
                onPersonInfoItemClickListener
        ));

        //点赞
        binding.btnLike.setOnClickListener(view -> onBottomFunctionClickListener.onLikeClick(forum));

        //收藏
        binding.btnCollection.setOnClickListener(view -> onBottomFunctionClickListener.onCollectionClick(forum));

        //评论
        binding.btnCommit.setOnClickListener(view -> onBottomFunctionClickListener.onCommentClick(forum));
    }

    public void setOnPersonInfoItemClickListener(DialogUtil.OnPersonInfoItemClickListener onPersonInfoItemClickListener) {
        this.onPersonInfoItemClickListener = onPersonInfoItemClickListener;
    }

    public void setOnBottomFunctionClickListener(OnBottomFunctionClickListener onBottomFunctionClickListener) {
        this.onBottomFunctionClickListener = onBottomFunctionClickListener;
    }

}
