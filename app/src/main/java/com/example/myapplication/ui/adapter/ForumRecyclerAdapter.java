package com.example.myapplication.ui.adapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemForumBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.Forum;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;

import java.util.ArrayList;

import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import okhttp3.ResponseBody;

public class ForumRecyclerAdapter extends BaseRecyclerAdapter<Forum, ItemForumBinding> {

    BGASortableNinePhotoLayout.Delegate delegate;

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
        //九宫格图片
        binding.rvImg.setData((ArrayList<String>) forum.getImgList());
        binding.rvImg.setDelegate(delegate);

        binding.ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focus(forum.getId());
            }
        });
    }

    private void focus(long userId) {
        ApiUtil.request(
                RetrofitHelper.getApiService().focusUser(userId),
                new ApiAction<ResultModel<ResponseBody>>() {
                    @Override
                    public void onSuccess(ResultModel<ResponseBody> response) {

                    }
                }
        );
    }

}
