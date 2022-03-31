package com.example.myapplication.ui.login;

import android.view.View;

import androidx.navigation.Navigation;

import com.example.myapplication.MainActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.login.LoginResultModel;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;
import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

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
        binding.btnLogin.setOnClickListener(view12 -> {
            login();
        });

        //去注册按钮
        binding.btnRegister.setOnClickListener(
                view1 -> Navigation.findNavController(view).navigate(R.id.register_fragment)
        );
    }

    private void login() {
        ApiUtil.request(
                RetrofitHelper.getApiService().login(
                        binding.etAccount.getText().toString(),
                        binding.etPassword.getText().toString()
                ),
                new ApiAction<ResultModel<LoginResultModel>>() {
                    @Override
                    public void onSuccess(ResultModel<LoginResultModel> response) {

                        //connectToRongCloud(); todo 用这个 不要用下面这行
                        loginToRongCloud("xxxxxx");
                        MainActivity.jumpToMain(getContext());
                        Hawk.put(HawkKey.KEY_EMAIL, binding.etAccount.getText().toString());
                        Hawk.put(HawkKey.KEY_PASSWORD, binding.etPassword.getText().toString());
                        Hawk.put(HawkKey.KEY_TOKEN, response.getData().getToken());
                        Hawk.put(HawkKey.KEY_HAS_LOGIN, true);
                    }
                });
    }

    private void connectToRongCloud() {
        ApiUtil.request(RetrofitHelper.getApiService().getRongCloudToken(), new ApiAction<ResultModel<String>>() {
            @Override
            public void onSuccess(ResultModel<String> response) {
                loginToRongCloud(response.getData());
            }
        });
    }

    public static void loginToRongCloud(String token1) {
        // 已为您替换开发者后台获取到的 userid 为 1 的用户 Token
        String token = "SrfwuVVXoF+ueTp7XNYV6sXTcHflFFyM@zwch.cn.rongnav.com;zwch.cn.rongcfg.com";
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String userId) {
                //登录成功
                MyApplication.rongCloudConnected = true;
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode connectionErrorCode) {
                connectionErrorCode.getValue();
            }

            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {
                databaseOpenStatus.getValue();
            }
        });
    }

}