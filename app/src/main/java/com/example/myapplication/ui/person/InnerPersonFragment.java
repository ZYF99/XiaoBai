package com.example.myapplication.ui.person;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
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
import java.util.function.Predicate;
import java.util.stream.Collectors;

import io.rong.imkit.utils.RouteUtils;
import io.rong.imlib.model.Conversation;

//内嵌的人物列表界面
public class InnerPersonFragment extends BaseFragment<FragmentInnerListBinding> {

    String tag;
    PersonRecyclerAdapter personRecyclerAdapter;
    boolean isFollowOther; //是否是"我的关注"界面
    List<Person> netList;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_inner_list;
    }

    public InnerPersonFragment(String tag) {
        this.tag = tag;
        this.isFollowOther = tag.equals("关注");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initView(View view) {
        personRecyclerAdapter = new PersonRecyclerAdapter(isFollowOther);
        binding.searchView.setVisibility(View.VISIBLE);
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                personRecyclerAdapter.replaceData(netList.stream().filter(
                        person -> person.getRealName().contains(newText)
                ).collect(Collectors.toList()));
                return false;
            }
        });
        binding.searchView.setOnCloseListener(() -> {
            personRecyclerAdapter.replaceData(netList);
            return false;
        });
        binding.refreshLayout.setOnRefreshListener(this::initList);
        personRecyclerAdapter.setOnInnerItemClickListener(new PersonRecyclerAdapter.OnInnerItemClickListener() {
            @Override
            public void onAvatarClick(Person person) {
                DialogUtil.showPersonInfoDialog(getContext(), person, person.isFollow(), new DialogUtil.OnPersonInfoItemClickListener() {
                    @Override
                    public void onFollowClick(Person person) {
                        followOrCancelUSer(person);
                    }

                    @Override
                    public void onUnFollowClick(Person person) {
                        followOrCancelUSer(person);
                    }

                    @Override
                    public void onSendMessageClick(Person person) {
                        RouteUtils.routeToConversationActivity(getContext(), Conversation.ConversationType.PRIVATE, String.valueOf(person.getId()), null);
                    }
                });
            }

            @Override
            public void onFollowClick(Person person) {
                followOrCancelUSer(person);
            }

            @Override
            public void onUnFollowClick(Person person) {
                followOrCancelUSer(person);
            }
        });
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvList.setAdapter(personRecyclerAdapter);
        initList();
    }

    private void initList() {
        binding.refreshLayout.setRefreshing(true);
        ApiUtil.request(RetrofitHelper.getApiService().fetchFansAndFollows(),
                new ApiAction<ResultModel<FetchFansAndFocusResultModel>>() {
                    @Override
                    public void onSuccess(ResultModel<FetchFansAndFocusResultModel> response) {
                        List<Person> list;
                        if (tag.equals("粉丝")) list = response.getData().getFans();
                        else list = response.getData().getFollows();
                        personRecyclerAdapter.replaceData(list);
                        netList = list;
                        binding.refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailed(Throwable t) {
                        super.onFailed(t);
                        binding.refreshLayout.setRefreshing(false);
                    }
                });
    }

    private void followOrCancelUSer(Person person) {
        ApiUtil.request(RetrofitHelper.getApiService().followOrCancelUser(person.getId()),
                new ApiAction<ResultModel<String>>() {
                    @Override
                    public void onSuccess(ResultModel<String> response) {
                        initList();
                    }
                });
    }

}
