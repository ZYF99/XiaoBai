package com.example.myapplication.ui.forum;

import static com.example.myapplication.ui.addforum.AddForumActivity.RC_PHOTO_PREVIEW;
import static com.example.myapplication.ui.forumdetail.ForumDetailActivity.KEY_FORUM_ID;

import android.content.Context;
import android.content.Intent;
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
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.ui.adapter.ForumRecyclerAdapter;
import com.example.myapplication.model.Forum;
import com.example.myapplication.ui.forumdetail.ForumDetailActivity;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;

//内嵌的论坛列表界面
public class InnerForumFragment extends Fragment implements BGASortableNinePhotoLayout.Delegate {

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
        forumRecyclerAdapter = new ForumRecyclerAdapter(this);
        rvForum.setLayoutManager(new LinearLayoutManager(getContext()));
        rvForum.setAdapter(forumRecyclerAdapter);
        forumRecyclerAdapter.setOnCellClickListener((itemForumBinding, forum) -> {
            jumpToForumDetail(getContext(), forum.getId());
        });
        initList();
    }

    private void initList() {
        boolean isFollow;
        isFollow = tag.equals("关注");
        /*ApiUtil.request(RetrofitHelper.getApiService().getForums(isFollow),
                new ApiAction<ResultModel<List<Forum>>>() {
                    @Override
                    public void onSuccess(ResultModel<List<Forum>> response) {
                        forumRecyclerAdapter.replaceData(response.getData());
                    }
                });*/
    }

    private void jumpToForumDetail(Context context, int forumId) {
        Intent intent = new Intent(context, ForumDetailActivity.class);
        intent.putExtra(KEY_FORUM_ID, forumId);
        context.startActivity(intent);
    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {

    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {

    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(getContext())
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(9) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        startActivityForResult(photoPickerPreviewIntent, RC_PHOTO_PREVIEW);
    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {

    }
}
