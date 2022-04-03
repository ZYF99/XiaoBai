package com.example.myapplication.ui.person;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentInnerListBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.account.FetchFansAndFocusResultModel;
import com.example.myapplication.ui.DialogUtil;
import com.example.myapplication.ui.adapter.PersonRecyclerAdapter;
import com.example.myapplication.model.Person;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;
import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import io.rong.imkit.utils.RouteUtils;
import io.rong.imlib.model.Conversation;
import okhttp3.ResponseBody;

//内嵌的人物列表界面
public class InnerPersonFragment extends BaseFragment<FragmentInnerListBinding> {

    String tag;
    PersonRecyclerAdapter personRecyclerAdapter;
    boolean isFollowOther; //是否是"我的关注"界面

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_inner_list;
    }

    public InnerPersonFragment(String tag) {
        this.tag = tag;
        this.isFollowOther = tag.equals("关注");
    }

    @Override
    public void initView(View view) {
        personRecyclerAdapter = new PersonRecyclerAdapter(isFollowOther);
        personRecyclerAdapter.setOnInnerItemClickListener(new PersonRecyclerAdapter.OnInnerItemClickListener() {
            @Override
            public void onAvatarClick(Person person) {
                DialogUtil.showPersonInfoDialog(getContext(), person, new DialogUtil.OnPersonInfoItemClickListener() {
                    @Override
                    public void onFollowClick(Person person) {
                        //关注

                    }

                    @Override
                    public void onUnFollowClick(Person person) {
                        //取消关注
                        unFollowPerson(person);
                    }

                    @Override
                    public void onSendMessageClick(Person person) {
                        //私聊
                        RouteUtils.routeToConversationActivity(getContext(), Conversation.ConversationType.PRIVATE, String.valueOf(person.getId()), null);
                    }
                });
            }

            @Override
            public void onUnFollowClick(Person person) {
                unFollowPerson(person);
            }
        });
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvList.setAdapter(personRecyclerAdapter);
        initList();
    }

    private void initList() {
        binding.refreshLayout.setRefreshing(true);
        ApiUtil.request(RetrofitHelper.getApiService().fetchFansAndFollows(Hawk.get(HawkKey.KEY_NAME)),
                new ApiAction<ResultModel<FetchFansAndFocusResultModel>>() {
                    @Override
                    public void onSuccess(ResultModel<FetchFansAndFocusResultModel> response) {
                        List<Person> list;
                        if (tag.equals("粉丝")) list = response.getData().getFans();
                        else list = response.getData().getFollows();
                        personRecyclerAdapter.replaceData(list);
                        binding.refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailed(Throwable t) {
                        super.onFailed(t);
                        binding.refreshLayout.setRefreshing(false);
                    }
                });
    }

    private void unFollowPerson(Person person) {
        ApiUtil.request(RetrofitHelper.getApiService().unFollowUser(person.getId()),
                new ApiAction<ResultModel<ResponseBody>>() {
                    @Override
                    public void onSuccess(ResultModel<ResponseBody> response) {
                        initList();
                    }
                });
    }

}
