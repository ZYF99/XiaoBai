package com.example.myapplication.ui.login;

import android.view.View;
import androidx.navigation.Navigation;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentRegisterBinding;
import com.example.myapplication.ui.base.BaseFragment;

public class RegisterFragment extends BaseFragment<FragmentRegisterBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register;
    }

    @Override
    public void initView(View view) {
        //注册并登录按钮
        binding.btnNext.setOnClickListener(view12 -> {

        });

        //返回登陆按钮
        binding.btnBack.setOnClickListener(view1 -> {
            Navigation.findNavController(view).popBackStack();
        });
    }
}
