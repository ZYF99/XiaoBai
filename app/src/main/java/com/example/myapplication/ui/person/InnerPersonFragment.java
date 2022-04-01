package com.example.myapplication.ui.person;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentInnerListBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.account.FetchFansAndFocusResultModel;
import com.example.myapplication.ui.adapter.PersonRecyclerAdapter;
import com.example.myapplication.model.Person;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;
import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;
import java.util.List;

//内嵌的人物列表界面
public class InnerPersonFragment extends BaseFragment<FragmentInnerListBinding> {

    String tag;
    PersonRecyclerAdapter personRecyclerAdapter;

    public InnerPersonFragment() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_inner_list;
    }

    public InnerPersonFragment(String tag) {
        this.tag = tag;
    }

    @Override
    public void initView(View view) {
        personRecyclerAdapter = new PersonRecyclerAdapter();
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvList.setAdapter(personRecyclerAdapter);
        initList();
    }

    private void initList() {
        ApiUtil.request(RetrofitHelper.getApiService().fetchFansAndFollows(Hawk.get(HawkKey.KEY_NAME)),
                new ApiAction<ResultModel<FetchFansAndFocusResultModel>>() {
                    @Override
                    public void onSuccess(ResultModel<FetchFansAndFocusResultModel> response) {
                        List<Person> list;
                        if (tag.equals("粉丝")) list = response.getData().getFans();
                        else list = response.getData().getFollows();
                        personRecyclerAdapter.replaceData(list);
                    }
                });
    }

}
