package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//教程主界面
public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //设置底部导航栏
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (!item.isChecked()) {
                    switch (item.getItemId()) {
                        case R.id.menu_home:
                            //跳转进入教程的页面
                            navController.navigate(R.id.tech_fragment);
                            break;
                        case R.id.menu_forum:
                            //跳转进入论坛页面
                            navController.navigate(R.id.forum_fragment);
                            break;
                        case R.id.menu_message:
                            //跳转进入消息页面
                            navController.navigate(R.id.message_fragment);
                            break;
                        case R.id.menu_mine:
                            //跳转进入我的页面
                            navController.navigate(R.id.mine_fragment);
                            break;
                    }
                }
                return true;
            }
        });
    }
}