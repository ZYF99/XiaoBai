package com.example.myapplication.ui.secret;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityModifyPasswordBinding;
import com.example.myapplication.databinding.ActivitySecretBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.account.EditUserInfoRequestModel;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.ui.personinfo.PersonInfoActivity;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;
import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;

//设置——隐私设置界面
public class SecretActivity extends BaseActivity<ActivitySecretBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_secret;
    }

    @Override
    public void initView(View view) {
        //退出到设置页面
        binding.toolBar.setNavigationOnClickListener(view1 -> finish());

        //关注我的人才能评论
        binding.switchComment.setChecked(Hawk.get(HawkKey.KEY_IS_COMMENT));
        binding.switchComment.setOnCheckedChangeListener((compoundButton, b) -> {
            EditUserInfoRequestModel editUserInfoRequestModel = new EditUserInfoRequestModel();
            editUserInfoRequestModel.setId(Hawk.get(HawkKey.KEY_ID));
            editUserInfoRequestModel.setComment(b);
            ApiUtil.request(RetrofitHelper.getApiService().updateUserInfo(editUserInfoRequestModel),
                    new ApiAction<ResultModel<String>>() {
                        @Override
                        public void onSuccess(ResultModel<String> response) {
                            Hawk.put(HawkKey.KEY_IS_COMMENT, b);
                            Toast.makeText(SecretActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }
}