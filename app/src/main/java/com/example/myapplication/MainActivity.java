package com.example.myapplication;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.account.FetchUserInfoResultModel;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;
import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;

import io.rong.imkit.RongIM;
import io.rong.imkit.userinfo.RongUserInfoManager;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

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

        ApiUtil.request(RetrofitHelper.getApiService().getRongCloudToken(
                Hawk.get(HawkKey.KEY_EMAIL),
                Hawk.get(HawkKey.KEY_ID).toString()
                ),
                new ApiAction<ResultModel<String>>() {
                    @Override
                    public void onSuccess(ResultModel<String> rongResult) {
                        loginToRongCloud(rongResult.getData());
                    }
                });

    }

    private void loginToRongCloud(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String userId) {
                //登录成功
                MyApplication.rongCloudConnected = true;

                RongUserInfoManager.getInstance().setUserInfoProvider(userId1 -> {
                    ApiUtil.request(RetrofitHelper.getApiService().getUserInfoById(userId1), new ApiAction<ResultModel<FetchUserInfoResultModel>>() {
                        @Override
                        public void onSuccess(ResultModel<FetchUserInfoResultModel> response) {
                            UserInfo userInfo = new UserInfo(
                                    response.getData().getId().toString(),
                                    response.getData().getRealName(),
                                    Uri.parse(response.getData().getAvatar())
                            );
                            RongUserInfoManager.getInstance().refreshUserInfoCache(userInfo);
                        }
                    });
                    return null;
                },true);
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode connectionErrorCode) {
                connectionErrorCode.getValue();
            }

            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {
                databaseOpenStatus.getValue();
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