package com.example.myapplication.ui.adapter;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemForumBinding;
import com.example.myapplication.model.Forum;

import java.util.ArrayList;

import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;

public class ForumRecyclerAdapter extends BaseRecyclerAdapter<Forum, ItemForumBinding> {

    BGASortableNinePhotoLayout.Delegate delegate;

    public ForumRecyclerAdapter(BGASortableNinePhotoLayout.Delegate delegate){
        this.delegate = delegate;
    }

    @Override
    int getLayoutRes() {
        return R.layout.item_forum;
    }

    @Override
    public void bindData(ItemForumBinding binding, int position) {
        ArrayList<String> imgList = new ArrayList<>();
        String a = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic24.nipic.com%2F20121029%2F6741879_162040605197_2.jpg&refer=http%3A%2F%2Fpic24.nipic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651304271&t=992ec591f3d610c99ba2c0f1fea5d0bf";
        imgList.add(a);
        imgList.add(a);
        imgList.add(a);
        imgList.add(a);
        imgList.add(a);
        imgList.add(a);
        imgList.add(a);
        imgList.add(a);
        imgList.add(a);
        //九宫格图片
        binding.rvImg.setData(imgList);
        binding.rvImg.setDelegate(delegate);
    }

}
