package com.example.myapplication.ui.teach;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentTechBinding;
import com.example.myapplication.ui.base.BaseFragment;

public class TechFragment extends BaseFragment<FragmentTechBinding> {
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_tech;
    }

    @Override
    public void initView(View view) {

        //跳转进入软件安装页面
        TextView firstEdit = view.findViewById((R.id.main3_edittext1));
        firstEdit = view.findViewById((R.id.main3_edittext1));
        firstEdit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SoftInstallActivity.class);
            startActivity(intent);

        });

        //跳转进入文件管理页面
        TextView secondEdit = view.findViewById((R.id.main3_edittext2));
        secondEdit = view.findViewById((R.id.main3_edittext2));
        secondEdit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), FileManageActivity.class);
            startActivity(intent);

        });

        //跳转进入办公软件页面
        TextView thirdEdit = view.findViewById((R.id.main3_edittext3));
        thirdEdit = view.findViewById((R.id.main3_edittext3));
        thirdEdit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SoftWareActivity.class);
            startActivity(intent);

        });

        //跳转进入电脑基础页面
        TextView forthEdit = view.findViewById((R.id.main3_edittext4));
        forthEdit = view.findViewById((R.id.main3_edittext4));
        forthEdit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ComputerStandardActivity.class);
            startActivity(intent);
        });
    }
}
