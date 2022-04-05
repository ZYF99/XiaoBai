package com.example.myapplication.ui.forum;

import static com.example.myapplication.ui.addforum.AddForumActivity.RC_PHOTO_PREVIEW;
import static com.example.myapplication.ui.forumdetail.ForumDetailActivity.KEY_FORUM_ID;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentInnerListBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.forum.Forum;
import com.example.myapplication.model.Person;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.ui.DialogUtil;
import com.example.myapplication.ui.adapter.ForumRecyclerAdapter;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.forumdetail.ForumDetailActivity;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import io.rong.imkit.utils.RouteUtils;
import io.rong.imlib.model.Conversation;
import okhttp3.ResponseBody;
import retrofit2.Call;

//内嵌的论坛列表界面
public class InnerForumFragment extends BaseFragment<FragmentInnerListBinding> implements BGASortableNinePhotoLayout.Delegate {

    String tag;
    ForumRecyclerAdapter forumRecyclerAdapter;

    public InnerForumFragment() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_inner_list;
    }

    public InnerForumFragment(String tag) {
        this.tag = tag;
    }

    @Override
    public void initView(View view) {
        forumRecyclerAdapter = new ForumRecyclerAdapter(this);
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvList.setAdapter(forumRecyclerAdapter);
        binding.refreshLayout.setOnRefreshListener(this::initList);

        forumRecyclerAdapter.setOnCellClickListener((itemForumBinding, forum) -> {
            jumpToForumDetail(getContext(), forum.getId());
        });
        forumRecyclerAdapter.setOnPersonInfoItemClickListener(new DialogUtil.OnPersonInfoItemClickListener() {
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
                RouteUtils.routeToConversationActivity(getContext(), Conversation.ConversationType.PRIVATE, String.valueOf(person.getId()), null);
            }
        });
        forumRecyclerAdapter.setOnBottomFunctionClickListener(new ForumRecyclerAdapter.OnBottomFunctionClickListener() {
            @Override
            public void onLikeClick(Forum forum) {

            }

            @Override
            public void onCollectionClick(Forum forum) {

            }

            @Override
            public void onCommentClick(Forum forum) {
                jumpToForumDetail(getContext(), forum.getId());
            }
        });

        initList();
    }

    private void initList() {
        binding.refreshLayout.setRefreshing(true);
        Call<ResultModel<List<Forum>>> call;
        switch (tag) {
            case "关注":
                call = RetrofitHelper.getApiService().getForums("FOLLOW");
                break;
            case "发现":
                call = RetrofitHelper.getApiService().getForums("ALL");
                break;
            case "笔记":
                call = RetrofitHelper.getApiService().getForums("MYFORUMS");
                break;
            case "收藏":
                call = RetrofitHelper.getApiService().getForums("COLLECTION");
                break;
            case "赞过":
                call = RetrofitHelper.getApiService().getForums("PRAISE");
                break;
            default:
                call = RetrofitHelper.getApiService().getForums("ALL");
                break;
        }
        ApiUtil.request(
                call,
                new ApiAction<ResultModel<List<Forum>>>() {
                    @Override
                    public void onSuccess(ResultModel<List<Forum>> response) {
                        forumRecyclerAdapter.replaceData(response.getData());
                        binding.refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailed(Throwable t) {
                        super.onFailed(t);
                        binding.refreshLayout.setRefreshing(false);
                    }
                });
    }

    private void followPerson(Person person) {
        ApiUtil.request(RetrofitHelper.getApiService().followOrCancelUser(person.getId()),
                new ApiAction<ResultModel<ResponseBody>>() {
                    @Override
                    public void onSuccess(ResultModel<ResponseBody> response) {

                    }
                });
    }

    private void unFollowPerson(Person person) {
        ApiUtil.request(RetrofitHelper.getApiService().followOrCancelUser(person.getId()),
                new ApiAction<ResultModel<ResponseBody>>() {
                    @Override
                    public void onSuccess(ResultModel<ResponseBody> response) {
                        initList();
                    }
                });
    }

    private void jumpToForumDetail(Context context, long forumId) {
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
