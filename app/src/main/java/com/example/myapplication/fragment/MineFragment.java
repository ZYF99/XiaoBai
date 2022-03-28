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
import com.example.myapplication.FansActivity;
import com.example.myapplication.MainActivity17;
import com.example.myapplication.R;

public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //跳转进入设置页面
        TextView tvSetting = view.findViewById((R.id.tv_setting));
        tvSetting.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), MainActivity17.class);
            startActivity(intent);
        });

        //跳转进入粉丝页面
        view.findViewById(R.id.tv_fans).setOnClickListener(view12 -> {
            Intent intent = new Intent(getContext(), FansActivity.class);
            startActivity(intent);
        });

        /*
        //跳转进入笔记页面
        TextView forthEdit = view.findViewById((R.id.main5_edittext5));
        forthEdit = view.findViewById((R.id.main5_edittext5));
        forthEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity15.class);
                startActivity(intent);

            }
        });

        //跳转进入软件安装页面
        TextView fifthEdit = view.findViewById((R.id.main5_edittext6));
        fifthEdit = view.findViewById((R.id.main5_edittext6));
        fifthEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity16.class);
                startActivity(intent);

            }
        });*/

    }
}
