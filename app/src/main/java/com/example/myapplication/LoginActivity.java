package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//登录界面
public class LoginActivity extends AppCompatActivity {
    EditText et_password;
    EditText et_name;
    Button check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView X = findViewById(R.id.tv_forget);
        et_password = findViewById(R.id.et_2);
        check = findViewById(R.id.loadin);
        et_name = findViewById(R.id.et_1);
        //点击登录
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }


}