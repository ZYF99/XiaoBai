package com.example.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.ui.base.BaseFragment;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView(View view) {
        //登录按钮
        binding.btnLogin.setOnClickListener(
                view1 -> getContext().startActivity(
                        new Intent(getContext(), MainActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)
                )
        );

        //去注册按钮
        binding.btnRegister.setOnClickListener(
                view1 -> Navigation.findNavController(view).navigate(R.id.register_fragment)
        );
    }
}