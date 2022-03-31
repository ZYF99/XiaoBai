package com.example.myapplication;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.manager.ApiService;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//教程主界面
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    NavController navController;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //设置底部导航栏
        binding.bottomNavView.setOnNavigationItemSelectedListener(item -> {
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
        });

        //拉取用户信息
        ApiUtil.request(RetrofitHelper.getApiService().getUserInfo(),
                new ApiAction<ResultModel<ResponseBody>>() {
                    @Override
                    public void onSuccess(ResultModel<ResponseBody> response) {

                    }
                });

    }

    public static void jumpToMain(Context context) {
        context.startActivity(
                new Intent(context, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)
        );
    }
}