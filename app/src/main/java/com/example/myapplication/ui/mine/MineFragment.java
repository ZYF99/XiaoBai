package com.example.myapplication.ui.mine;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myapplication.databinding.FragmentMineBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.account.FetchUserInfoResultModel;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.ui.person.FansActivity;
import com.example.myapplication.ui.setting.SettingActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.adapter.TabFragmentAdapter;
import com.example.myapplication.ui.forum.InnerForumFragment;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;
import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class MineFragment extends BaseFragment<FragmentMineBinding> {

    TabFragmentAdapter vpAdapter;
    List<Fragment> forumFragments;
    List<String> titles;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view) {

        //跳转进入设置页面
        TextView tvSetting = view.findViewById((R.id.tv_setting));
        tvSetting.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), SettingActivity.class);
            startActivity(intent);
        });

        //跳转进入粉丝页面
        view.findViewById(R.id.tv_fans).setOnClickListener(view12 -> {
            Intent intent = new Intent(getContext(), FansActivity.class);
            startActivity(intent);
        });


        forumFragments = new ArrayList<>();
        forumFragments.add(new InnerForumFragment("笔记"));
        forumFragments.add(new InnerForumFragment("收藏"));
        forumFragments.add(new InnerForumFragment("赞过"));
        titles = new ArrayList<>();
        titles.add("笔记");
        titles.add("收藏");
        titles.add("赞过");
        vpAdapter = new TabFragmentAdapter(getChildFragmentManager(), forumFragments, titles);
        binding.vpForum.setAdapter(vpAdapter);
        binding.tlForum.setupWithViewPager(binding.vpForum);

        //拉取用户信息
        ApiUtil.request(RetrofitHelper.getApiService().getUserInfo(),
                new ApiAction<ResultModel<FetchUserInfoResultModel>>() {
                    @Override
                    public void onSuccess(ResultModel<FetchUserInfoResultModel> response) {
                        Glide.with(binding.ivAvatar.getContext()).load(response.getData().getAvatar()).into(binding.ivAvatar);
                        binding.tvUserName.setText(response.getData().getRealName());
                        Hawk.put(HawkKey.KEY_IS_COMMENT, response.getData().isComment());
                    }
                });
    }
}
