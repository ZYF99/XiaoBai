package com.example.myapplication.ui.setting;

import android.content.Intent;
import android.view.View;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivitySettingBinding;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.ui.login.LoginActivity;
import com.example.myapplication.ui.modifypassword.ModifyPasswordActivity;
import com.example.myapplication.ui.personinfo.PersonInfoActivity;
import com.example.myapplication.ui.secret.SecretActivity;
import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;

//我的——设置界面
public class SettingActivity extends BaseActivity<ActivitySettingBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView(View view) {
        //退出进入我的页面
        binding.toolBar.setNavigationOnClickListener(view1 -> finish());
        //进入修改密码页面
        binding.btnAccount.setOnClickListener(view12 -> startActivity(new Intent(SettingActivity.this, ModifyPasswordActivity.class)));
       //进入个人资料页面
        binding.btnPersonInfo.setOnClickListener(view12 -> startActivity(new Intent(SettingActivity.this, PersonInfoActivity.class)));
       //进入隐私设置页面
        binding.btnSecret.setOnClickListener(view12 -> startActivity(new Intent(SettingActivity.this, SecretActivity.class)));
        //退出登陆
        binding.btnLogout.setOnClickListener(view13 -> {
            startActivity(
                    new Intent(SettingActivity.this, LoginActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)
            );
            Hawk.put(HawkKey.KEY_HAS_LOGIN,false);
        });
    }
}