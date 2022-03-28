package com.example.myapplication;
//我的——粉丝界面
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FansActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fans);
        //跳转返回我的页面
        TextView firstEdit = findViewById((R.id.main14_edittext9));
        firstEdit=findViewById((R.id.main14_edittext9));
        firstEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FansActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
        //跳转进入关注页面
        TextView secondEdit = findViewById((R.id.main14_edittext2));
        secondEdit=findViewById((R.id.main14_edittext2));
        secondEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FansActivity.this, FocusActivity.class);
                startActivity(intent);

            }
        });
    }
}