package com.example.myapplication.ui.modifypassword;

import android.view.View;
import android.widget.Toast;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityModifyPasswordBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.login.ResetPasswordRequestModel;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;
import com.example.myapplication.util.CountDownTimerUtil;
import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;
import okhttp3.ResponseBody;

//设置——修改密码界面
public class ModifyPasswordActivity extends BaseActivity<ActivityModifyPasswordBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_modify_password;
    }

    @Override
    public void initView(View view) {

        //返回按钮
        binding.toolBar.setNavigationOnClickListener(view1 -> finish());

        //验证码按钮
        binding.tvSendEmail.setOnClickListener(view13 -> triggerSendEmailCode());

        //确认修改按钮
        binding.btnConfirm.setOnClickListener(view12 -> modify());

    }

    //触发验证码发送
    private void triggerSendEmailCode() {
        CountDownTimerUtil.sendEmailCodeTimer(binding.tvSendEmail).start();
        ApiUtil.request(RetrofitHelper.getApiService().sendEmailCodeForResetPassword(binding.etEmailCode.getText().toString()),
                new ApiAction<ResultModel<ResponseBody>>() {
                    @Override
                    public void onSuccess(ResultModel<ResponseBody> response) {

                    }
                });
    }

    //修改
    private void modify() {
        if (!binding.editPassword.getText().toString().equals(binding.editRepassword.getText().toString())) {
            Toast.makeText(ModifyPasswordActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiUtil.request(
                RetrofitHelper.getApiService()
                        .resetPassword(
                                new ResetPasswordRequestModel(
                                        binding.editPassword.getText().toString(),
                                        Hawk.get(HawkKey.KEY_PASSWORD),
                                        binding.etEmailCode.getText().toString()
                                )
                        ),
                new ApiAction<ResultModel<ResponseBody>>() {
                    @Override
                    public void onSuccess(ResultModel<ResponseBody> response) {

                    }
                }
        );
    }
}