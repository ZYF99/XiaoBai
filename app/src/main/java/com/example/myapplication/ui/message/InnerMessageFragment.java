package com.example.myapplication.ui.message;

import static com.example.myapplication.ui.forumdetail.ForumDetailActivity.KEY_FORUM_ID;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentInnerListBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.ui.adapter.MessageRecyclerAdapter;
import com.example.myapplication.model.Message;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.forumdetail.ForumDetailActivity;
import com.example.myapplication.ui.person.FansActivity;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;

import java.util.List;

import retrofit2.Call;

//内嵌的消息列表界面
public class InnerMessageFragment extends BaseFragment<FragmentInnerListBinding> {

    String tag;
    MessageRecyclerAdapter messageRecyclerAdapter;

    public InnerMessageFragment() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_inner_list;
    }

    public InnerMessageFragment(String tag) {
        this.tag = tag;
    }

    @Override
    public void initView(View view) {
        messageRecyclerAdapter = new MessageRecyclerAdapter();
        messageRecyclerAdapter.setOnCellClickListener((itemMessageBinding, message) -> {
            switch (message.getType()) {
                case "PRAISE":
                case "COLLECTION":
                case "COMMENT":
                    jumpToForumDetail(message.getForum().getId());
                    break;
                case "FOLLOW":
                    Intent intent = new Intent(getContext(), FansActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        });
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvList.setAdapter(messageRecyclerAdapter);
        binding.refreshLayout.setOnRefreshListener(this::initList);
        initList();
    }

    private void initList() {

        binding.refreshLayout.setRefreshing(true);
        Call<ResultModel<List<Message>>> call;
        switch (tag) {
            case "赞和收藏":
                call = RetrofitHelper.getApiService().getLoginUserPraiseOrCollectionList();
                break;
            case "新增关注":
                call = RetrofitHelper.getApiService().getLoginUserFollowList();
                break;
            case "评论":
                call = RetrofitHelper.getApiService().getLoginUserCommentList();
                break;
            default:
                call = RetrofitHelper.getApiService().getLoginUserPraiseOrCollectionList();
                break;
        }
        ApiUtil.request(
                call,
                new ApiAction<ResultModel<List<Message>>>() {
                    @Override
                    public void onSuccess(ResultModel<List<Message>> response) {
                        messageRecyclerAdapter.replaceData(response.getData());
                        binding.refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailed(Throwable t) {
                        super.onFailed(t);
                        binding.refreshLayout.setRefreshing(false);
                    }
                });
    }

    //跳转至论坛详情
    private void jumpToForumDetail(long id) {
        Intent intent = new Intent(getContext(), ForumDetailActivity.class);
        intent.putExtra(KEY_FORUM_ID, id);
        getContext().startActivity(intent);
    }

}
