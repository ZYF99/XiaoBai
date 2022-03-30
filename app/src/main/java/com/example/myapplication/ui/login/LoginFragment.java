package com.example.myapplication.ui.login;

import android.util.Log;
import android.view.View;

import androidx.navigation.Navigation;

import com.example.myapplication.MainActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.manager.RetrofitHelper;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView(View view) {
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
        RetrofitHelper.getApiService()
                .login(
                        binding.etAccount.getText().toString(),
                        binding.etPassword.getText().toString()
                )
                .enqueue(new Callback<ResultModel<String>>() {
                    @Override
                    public void onResponse(Call<ResultModel<String>> call, Response<ResultModel<String>> response) {
                        Log.d("!!!!!login success", response.body().getMsg());
                        //connectToRongCloud(); todo 用这个 不要用下面这行
                        loginToRongCloud("xxxxxx");
                        MainActivity.jumpToMain(getContext());
                    }

                    @Override
                    public void onFailure(Call<ResultModel<String>> call, Throwable t) {
                        Log.e("!!!!!login failed", t.getMessage());
                    }
                });
    }

    private void connectToRongCloud() {
        RetrofitHelper.getApiService().getRongCloudToken().enqueue(
                new Callback<ResultModel<String>>() {
                    @Override
                    public void onResponse(Call<ResultModel<String>> call, Response<ResultModel<String>> response) {
                        loginToRongCloud(response.body().getData());
                    }

                    @Override
                    public void onFailure(Call<ResultModel<String>> call, Throwable t) {

                    }
                }
        );
    }

    private void loginToRongCloud(String token1) {
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