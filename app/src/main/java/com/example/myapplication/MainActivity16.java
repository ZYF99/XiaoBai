package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//我的——收藏界面
public class MainActivity16 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);
        //跳转进入教程页面
        Button Btn1=findViewById(R.id.main_16button1);
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity16.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //跳转进入论坛页面
        Button Btn2=findViewById(R.id.main_16button2);
        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity16.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //跳转进入设置页面
        TextView firstEdit = findViewById((R.id.main16_edittext2));
        firstEdit=findViewById((R.id.main16_edittext2));
        firstEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity16.this,MainActivity17.class);
                startActivity(intent);

            }
        });
        //跳转进入关注页面
        TextView secondEdit = findViewById((R.id.main16_edittext3));
        secondEdit=findViewById((R.id.main16_edittext3));
        secondEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity16.this, FocusActivity.class);
                startActivity(intent);

            }
        });
        //跳转进入粉丝页面
        TextView thirdEdit = findViewById((R.id.main16_edittext4));
        thirdEdit=findViewById((R.id.main16_edittext4));
        thirdEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity16.this, FansActivity.class);
                startActivity(intent);

            }
        });
        //跳转进入笔记页面
        TextView forthEdit = findViewById((R.id.main16_edittext5));
        forthEdit=findViewById((R.id.main16_edittext5));
        forthEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity16.this,MainActivity15.class);
                startActivity(intent);

            }
        });
    }
}