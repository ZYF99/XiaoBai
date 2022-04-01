package com.example.myapplication.ui.login;

import static com.example.myapplication.ui.login.LoginFragment.login;

import android.view.View;

import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentRegisterBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.login.RegisterRequestModel;
import com.example.myapplication.ui.base.BaseFragment;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;
import com.example.myapplication.util.CountDownTimerUtil;

import okhttp3.ResponseBody;

public class RegisterFragment extends BaseFragment<FragmentRegisterBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register;
    }

    @Override
    public void initView(View view) {
        //注册并登录按钮
        binding.btnNext.setOnClickListener(view12 -> registerAndLogin());

        //验证码按钮
        binding.tvSendEmail.setOnClickListener(view13 -> triggerSendEmailCode());

        //返回登陆按钮
        binding.btnBack.setOnClickListener(view1 -> Navigation.findNavController(view).popBackStack());
    }

    //触发验证码发送
    private void triggerSendEmailCode() {
        ApiUtil.request(RetrofitHelper.getApiService().sendEmailCodeForRegister(binding.etEmail.getText().toString()),
                new ApiAction<ResultModel<ResponseBody>>() {
                    @Override
                    public void onSuccess(ResultModel<ResponseBody> response) {
                        CountDownTimerUtil.sendEmailCodeTimer(binding.tvSendEmail).start();
                    }
                });
    }

    //注册并登陆
    private void registerAndLogin() {
        ApiUtil.request(RetrofitHelper.getApiService()
                        .register(
                                new RegisterRequestModel(
                                        binding.etEmail.getText().toString(),
                                        binding.editPassword.getText().toString(),
                                        binding.etName.getText().toString(),
                                        binding.etVerifyCode.getText().toString()
                                )
                        ),
                new ApiAction<ResultModel<ResponseBody>>() {
                    @Override
                    public void onSuccess(ResultModel<ResponseBody> response) {
                        login(getContext(), binding.etEmail.getText().toString(), binding.editPassword.getText().toString());
                    }
                });
    }


}
