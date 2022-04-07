package com.example.myapplication.ui.forumdetail;

import static com.example.myapplication.ui.addforum.AddForumActivity.RC_PHOTO_PREVIEW;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityForumDetailBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.Person;
import com.example.myapplication.model.forum.AddCommentRequestModel;
import com.example.myapplication.model.forum.Forum;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.ui.DialogUtil;
import com.example.myapplication.ui.adapter.CommentRecyclerAdapter;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;
import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import io.rong.imkit.utils.RouteUtils;
import io.rong.imlib.model.Conversation;

public class ForumDetailActivity extends BaseActivity<ActivityForumDetailBinding> implements BGASortableNinePhotoLayout.Delegate {
    public static final String KEY_FORUM_ID = "key_forum_id";

    CommentRecyclerAdapter commentRecyclerAdapter;

    long id;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_forum_detail;
    }

    @Override
    public void initView(View view) {

        id = getIntent().getLongExtra(KEY_FORUM_ID, 0);

        //退出到设置页面
        binding.toolBar.setNavigationOnClickListener(view1 -> finish());
        commentRecyclerAdapter = new CommentRecyclerAdapter();
        binding.rvComment.setLayoutManager(new LinearLayoutManager(binding.rvComment.getContext()));
        binding.rvComment.setAdapter(commentRecyclerAdapter);
        commentRecyclerAdapter.setOnPersonInfoItemClickListener(new DialogUtil.OnPersonInfoItemClickListener() {
            @Override
            public void onFollowClick(Person person) {
                followOrCancelPerson(person);
            }

            @Override
            public void onUnFollowClick(Person person) {
                followOrCancelPerson(person);
            }

            @Override
            public void onSendMessageClick(Person person) {
                RouteUtils.routeToConversationActivity(ForumDetailActivity.this, Conversation.ConversationType.PRIVATE, String.valueOf(person.getId()), null);
            }
        });

        //九宫格事件
        binding.snplMomentAddPhotos.setDelegate(this);

        //删除
        binding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog();
            }
        });

        //评论条
        binding.tvComment.setOnClickListener(view12 -> {
            binding.llComment.setVisibility(View.VISIBLE);
            binding.editComment.requestFocus();
        });

        //评论按钮
        binding.btnSend.setOnClickListener(view13 -> {
            String content = binding.editComment.getText().toString();
            if (content.isEmpty()) return;
            comment(id, content);
        });

        //拉取论坛详情
        fetchForumDetail(id);
    }

    //根据id拉取论坛详情
    private void fetchForumDetail(long id) {
        ApiUtil.request(
                RetrofitHelper.getApiService().getForumDetail(id),
                new ApiAction<ResultModel<Forum>>() {
                    @Override
                    public void onSuccess(ResultModel<Forum> response) {
                        //标题
                        binding.toolBar.setTitle(response.getData().getRealName());

                        //内容
                        binding.tvContent.setText(response.getData().getArticleText());

                        //九宫格图片
                        binding.snplMomentAddPhotos.setData(response.getData().getImageList());

                        //评论列表
                        commentRecyclerAdapter.replaceData(response.getData().getCommentList());

                        //删除按钮
                        if (response.getData().getUserId() == Hawk.get(HawkKey.KEY_ID, 0l)) {
                            binding.ivDelete.setVisibility(View.VISIBLE);
                        }else {
                            binding.ivDelete.setVisibility(View.GONE);
                        }

                        //点赞
                        String likePrefString = response.getData().isPraise() ? "已赞" : "赞";
                        binding.tvLike.setText(likePrefString + " " + response.getData().getPraiseNum());
                        binding.tvLike.setOnClickListener(view -> like(id));

                        //收藏
                        String collectionPrefString = response.getData().isCollect() ? "已收藏" : "收藏";
                        binding.tvCollection.setText(collectionPrefString + " " + response.getData().getCollectNum());
                        binding.tvCollection.setOnClickListener(view -> collect(id));

                    }
                }
        );

    }

    //删除弹窗
    private void showDeleteDialog() {
        new AlertDialog.Builder(ForumDetailActivity.this)
                .setTitle("确定要删除该内容吗？")
                .setPositiveButton("确定", (dialogInterface, i) -> {
                    ApiUtil.request(RetrofitHelper.getApiService().deleteForum(id), new ApiAction<ResultModel<String>>() {
                        @Override
                        public void onSuccess(ResultModel<String> response) {
                            finish();
                        }
                    });
                })
                .setNegativeButton("取消", (dialogInterface, i) -> {

                })
                .create()
                .show();
    }

    //关注或取消关注
    private void followOrCancelPerson(Person person) {
        ApiUtil.request(RetrofitHelper.getApiService().followOrCancelUser(person.getId()),
                new ApiAction<ResultModel<String>>() {
                    @Override
                    public void onSuccess(ResultModel<String> response) {
                        fetchForumDetail(id);
                    }
                });
    }

    //点赞
    private void like(long id) {
        ApiUtil.request(RetrofitHelper.getApiService().praiseOrCollection(id, 1),
                new ApiAction<ResultModel<String>>() {
                    @Override
                    public void onSuccess(ResultModel<String> response) {
                        fetchForumDetail(id);
                    }
                });
    }

    //收藏
    private void collect(long id) {
        ApiUtil.request(RetrofitHelper.getApiService().praiseOrCollection(id, 2),
                new ApiAction<ResultModel<String>>() {
                    @Override
                    public void onSuccess(ResultModel<String> response) {
                        fetchForumDetail(id);
                    }
                });
    }

    //评论
    private void comment(long id, String content) {
        ApiUtil.request(RetrofitHelper.getApiService().addComment(
                new AddCommentRequestModel(content, id)
                ),
                new ApiAction<ResultModel<String>>() {
                    @Override
                    public void onSuccess(ResultModel<String> response) {
                        binding.llComment.setVisibility(View.GONE);
                        binding.editComment.setText("");
                        fetchForumDetail(id);
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
