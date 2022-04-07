package com.example.myapplication.ui.adapter;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemForumBinding;
import com.example.myapplication.model.forum.Forum;
import com.example.myapplication.model.Person;
import com.example.myapplication.ui.DialogUtil;
import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;

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
        binding.tvTime.setText(forum.getCreateTime().split("T")[0]);

        //九宫格图片
        binding.rvImg.setData((ArrayList<String>) forum.getImageList());
        binding.rvImg.setDelegate(delegate);

        //头像
        binding.ivAvatar.setOnClickListener(view -> {
            if (!forum.getCreatName().equals(Hawk.get(HawkKey.KEY_EMAIL))) {
                DialogUtil.showPersonInfoDialog(
                        binding.ivAvatar.getContext(),
                        new Person(forum.getCreatName(), forum.getUserId(), forum.getPhotoPath(), forum.getRealName(), forum.isFollow()),
                        forum.isFollow(),
                        onPersonInfoItemClickListener
                );
            }
        });

        //点赞
        String likePrefString = forum.isPraise() ? "已赞" : "赞";
        binding.tvLike.setText(likePrefString + " " + forum.getPraiseNum());
        binding.tvLike.setOnClickListener(view -> onBottomFunctionClickListener.onLikeClick(forum));

        //收藏
        String collectionPrefString = forum.isCollect() ? "已收藏" : "收藏";
        binding.tvCollection.setText(collectionPrefString + " " + forum.getCollectNum());
        binding.tvCollection.setOnClickListener(view -> onBottomFunctionClickListener.onCollectionClick(forum));

        //评论
        binding.tvComment.setOnClickListener(view -> onBottomFunctionClickListener.onCommentClick(forum));
    }

    public void setOnPersonInfoItemClickListener(DialogUtil.OnPersonInfoItemClickListener onPersonInfoItemClickListener) {
        this.onPersonInfoItemClickListener = onPersonInfoItemClickListener;
    }

    public void setOnBottomFunctionClickListener(OnBottomFunctionClickListener onBottomFunctionClickListener) {
        this.onBottomFunctionClickListener = onBottomFunctionClickListener;
    }

}
