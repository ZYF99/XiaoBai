package com.example.myapplication.ui.forumdetail;

import static com.example.myapplication.ui.addforum.AddForumActivity.RC_PHOTO_PREVIEW;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityForumDetailBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.Person;
import com.example.myapplication.model.forum.Comment;
import com.example.myapplication.model.forum.Forum;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.ui.DialogUtil;
import com.example.myapplication.ui.adapter.CommentRecyclerAdapter;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import io.rong.imkit.utils.RouteUtils;
import io.rong.imlib.model.Conversation;
import okhttp3.ResponseBody;

public class ForumDetailActivity extends BaseActivity<ActivityForumDetailBinding> implements BGASortableNinePhotoLayout.Delegate {
    public static final String KEY_FORUM_ID = "key_forum_id";

    CommentRecyclerAdapter commentRecyclerAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_forum_detail;
    }

    @Override
    public void initView(View view) {
        //退出到设置页面
        binding.toolBar.setNavigationOnClickListener(view1 -> finish());
        commentRecyclerAdapter = new CommentRecyclerAdapter();
        binding.rvComment.setLayoutManager(new LinearLayoutManager(binding.rvComment.getContext()));
        binding.rvComment.setAdapter(commentRecyclerAdapter);
        commentRecyclerAdapter.setOnPersonInfoItemClickListener(new DialogUtil.OnPersonInfoItemClickListener() {
            @Override
            public void onFollowClick(Person person) {
                followPerson(person);
            }

            @Override
            public void onUnFollowClick(Person person) {
                unFollowPerson(person);
            }

            @Override
            public void onSendMessageClick(Person person) {
                RouteUtils.routeToConversationActivity(ForumDetailActivity.this, Conversation.ConversationType.PRIVATE, String.valueOf(person.getId()), null);
            }
        });
        fetchForumDetail(getIntent().getIntExtra(KEY_FORUM_ID, 0));
    }

    private void fetchForumDetail(long id) {
        ApiUtil.request(
                RetrofitHelper.getApiService().getForumDetail(id),
                new ApiAction<ResultModel<Forum>>() {
                    @Override
                    public void onSuccess(ResultModel<Forum> response) {

                    }
                }
        );

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
        binding.snplMomentAddPhotos.setData(imgList);
        binding.snplMomentAddPhotos.setDelegate(this);

        //评论列表
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(new Person("2131423@qq.com", 959393354200645632l, "", "XXX"), "哈哈哈哈哈", 214124324));
        comments.add(new Comment(new Person("2131423@qq.com", 959393354200645632l, "", "XXX"), "测试测试", 214124324));
        comments.add(new Comment(new Person("2131423@qq.com", 959393354200645632l, "", "XXX"), "耶耶耶耶耶耶", 214124324));
        commentRecyclerAdapter.replaceData(comments);
    }

    private void followPerson(Person person) {
        ApiUtil.request(RetrofitHelper.getApiService().followUser(person.getId()),
                new ApiAction<ResultModel<ResponseBody>>() {
                    @Override
                    public void onSuccess(ResultModel<ResponseBody> response) {

                    }
                });
    }

    private void unFollowPerson(Person person) {
        ApiUtil.request(RetrofitHelper.getApiService().unFollowUser(person.getId()),
                new ApiAction<ResultModel<ResponseBody>>() {
                    @Override
                    public void onSuccess(ResultModel<ResponseBody> response) {

                    }
                });
    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {

    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {

    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(binding.snplMomentAddPhotos.getMaxItemCount()) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        startActivityForResult(photoPickerPreviewIntent, RC_PHOTO_PREVIEW);
    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {

    }
}
