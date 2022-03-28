package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.myapplication.SoftInstallActivity;
import com.example.myapplication.FileManageActivity;
import com.example.myapplication.MainActivity8;
import com.example.myapplication.MainActivity9;
import com.example.myapplication.R;

public class TechFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_tech, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
            Intent intent = new Intent(getContext(), MainActivity8.class);
            startActivity(intent);

        });

        //跳转进入电脑基础页面
        TextView forthEdit = view.findViewById((R.id.main3_edittext4));
        forthEdit = view.findViewById((R.id.main3_edittext4));
        forthEdit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainActivity9.class);
            startActivity(intent);
        });
    }
}
