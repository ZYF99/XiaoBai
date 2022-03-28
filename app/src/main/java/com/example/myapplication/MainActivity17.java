package com.example.myapplication;
//我的——设置界面

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity17 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);
        //退出进入我的页面
        TextView firstEdit = findViewById((R.id.main17_edittext9));
        firstEdit = findViewById((R.id.main17_edittext9));
        firstEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity17.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}