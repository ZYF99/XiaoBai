package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

//我的-关注界面
public class FocusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);
        //跳转返回我的页面
        TextView firstEdit = findViewById((R.id.main13_edittext9));
        firstEdit=findViewById((R.id.main13_edittext9));
        firstEdit.setOnClickListener(view -> {
            Intent intent=new Intent(FocusActivity.this,MainActivity.class);
            startActivity(intent);
        });
        //跳转进入粉丝页面
        TextView secondEdit = findViewById((R.id.main13_edittext3));
        secondEdit=findViewById((R.id.main13_edittext3));
        secondEdit.setOnClickListener(view -> {
            Intent intent=new Intent(FocusActivity.this, FansActivity.class);
            startActivity(intent);
        });
    }
}