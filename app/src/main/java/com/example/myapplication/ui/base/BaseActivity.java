package com.example.myapplication.ui.base;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<Binding extends ViewDataBinding> extends AppCompatActivity {

    public Binding binding;

    public BaseActivity() {

    }

    protected abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutRes());
        initView(binding.getRoot());
    }

    public abstract void initView(View view);

}