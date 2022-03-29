package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//我的-笔记界面
public class MainActivity15 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);


        //跳转进入教程页面
        Button Btn1=findViewById(R.id.main_15button1);
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity15.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //跳转进入论坛页面
        Button Btn2=findViewById(R.id.main_15button2);
        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity15.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //跳转进入设置页面
        TextView firstEdit = findViewById((R.id.main15_edittext2));
        firstEdit=findViewById((R.id.main15_edittext2));
        firstEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity15.this, SettingActivity.class);
                startActivity(intent);

            }
        });
        //跳转进入关注页面
        TextView secondEdit = findViewById((R.id.main15_edittext3));
        secondEdit=findViewById((R.id.main15_edittext3));
        secondEdit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity15.this, FocusActivity.class);
            startActivity(intent);

        }
    });
        //跳转进入粉丝页面
        TextView thirdEdit = findViewById((R.id.main15_edittext4));
        thirdEdit=findViewById((R.id.main15_edittext4));
        thirdEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity15.this, FansActivity.class);
                startActivity(intent);

            }
        });
        //跳转进入收藏页面
        TextView forthEdit = findViewById((R.id.main15_edittext6));
        forthEdit=findViewById((R.id.main15_edittext6));
        forthEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity15.this,MainActivity16.class);
                startActivity(intent);

            }
        });
    }
}