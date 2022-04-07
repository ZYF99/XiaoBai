package com.example.myapplication.ui.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.navigation.Navigation;

import com.example.myapplication.MainActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.account.FetchUserInfoResultModel;
import com.example.myapplication.model.login.LoginResultModel;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.ui.modifypassword.ModifyPasswordActivity;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;
import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;

import io.rong.imkit.RongIM;
import io.rong.imkit.userinfo.RongUserInfoManager;
import io.rong.imkit.userinfo.UserDataProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView(View view) {

        binding.etAccount.setText(Hawk.get(HawkKey.KEY_EMAIL, ""));
        binding.etPassword.setText(Hawk.get(HawkKey.KEY_PASSWORD, ""));

        //登录按钮
        binding.btnLogin.setOnClickListener(view12 -> login(getContext(), binding.etAccount.getText().toString(), binding.etPassword.getText().toString()));

        //去注册按钮
        binding.btnRegister.setOnClickListener(
                view1 -> Navigation.findNavController(view).navigate(R.id.register_fragment)
        );

        //忘记密码
        binding.btnForgetPassword.setOnClickListener(view13 -> startActivity(new Intent(getContext(), ModifyPasswordActivity.class)));
    }

    //登陆
    public static void login(Context context, String email, String password) {

        ApiUtil.request(
                RetrofitHelper.getApiService().login(
                        email,
                        password
                ),
                new ApiAction<ResultModel<LoginResultModel>>() {
                    @Override
                    public void onSuccess(ResultModel<LoginResultModel> loginResult) {
                        Hawk.put(HawkKey.KEY_TOKEN, loginResult.getData().getToken());
                        ApiUtil.request(RetrofitHelper.getApiService().getUserInfo(),
                                new ApiAction<ResultModel<FetchUserInfoResultModel>>() {
                                    @Override
                                    public void onSuccess(ResultModel<FetchUserInfoResultModel> userInfo) {
                                        Hawk.put(HawkKey.KEY_NAME, userInfo.getData().getRealName());
                                        Hawk.put(HawkKey.KEY_EMAIL, email);
                                        Hawk.put(HawkKey.KEY_PASSWORD, password);
                                        Hawk.put(HawkKey.KEY_HAS_LOGIN, true);
                                        Hawk.put(HawkKey.KEY_ID, userInfo.getData().getId());
                                        Hawk.put(HawkKey.KEY_AVATAR, userInfo.getData().getAvatar());
                                        MainActivity.jumpToMain(context);
                                    }
                                });
                    }
                });
    }

}