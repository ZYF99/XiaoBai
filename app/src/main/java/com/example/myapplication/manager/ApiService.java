package com.example.myapplication.manager;

import com.example.myapplication.model.forum.Forum;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.account.FetchFansAndFocusResultModel;
import com.example.myapplication.model.account.FetchUserInfoResultModel;
import com.example.myapplication.model.login.LoginResultModel;
import com.example.myapplication.model.login.ResetPasswordRequestModel;
import com.example.myapplication.model.login.RegisterRequestModel;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    //*********************************************账户相关******************************************、

    //登录
    @FormUrlEncoded
    @POST("myWeb/login")
    Call<ResultModel<LoginResultModel>> login(@Field("email") String email, @Field("password") String password);

    //获取登录用户信息
    @GET("myWeb/user/getLoginUserInfo")
    Call<ResultModel<FetchUserInfoResultModel>> getUserInfo();

    //根据id获取用户信息
    @GET("myWeb/user/getUserInfoById")
    Call<ResultModel<FetchUserInfoResultModel>> getUserInfoById();

    //注册用户
    @POST("myWeb/sys/registerUser")
    Call<ResultModel<ResponseBody>> register(@Body RegisterRequestModel registerRequestModel);

    //重置密码
    @POST("myWeb/sys/resetPassword")
    Call<ResultModel<ResponseBody>> resetPassword(@Body ResetPasswordRequestModel resetPasswordRequestModel);

    //发送验证码-注册界面
    @GET("myWeb/sys/sendVerificationCodeForRegister")
    Call<ResultModel<ResponseBody>> sendEmailCodeForRegister(@Query("email") String email);

    //发送验证码-重置密码界面
    @GET("myWeb/sys/sendVerificationCodeForReset")
    Call<ResultModel<ResponseBody>> sendEmailCodeForResetPassword(@Query("email") String email);

    //获取融云token
    @GET("myWeb/rongyun/getToken")
    Call<ResultModel<String>> getRongCloudToken(@Query("email") String email, @Query("id") String id);

    //获取粉丝和关注的人
    @GET("myWeb/userRelation/getLoginUserRelation")
    Call<ResultModel<FetchFansAndFocusResultModel>> fetchFansAndFollows(@Query("realName") String realName);

    //关注用户
    @GET("myWeb/userRelation/addLoginUserRelation")
    Call<ResultModel<ResponseBody>> followUser(@Query("userId") Long userId);

    //取消关注用户
    @GET("myWeb/userRelation/removeLoginUserRelation")
    Call<ResultModel<ResponseBody>> unFollowUser(@Query("userId") Long userId);

    //*********************************************论坛相关******************************************

    //获取论坛列表，isFollow为true时获取关注的人的论坛
    @GET("myWeb/forum/getLoginUserForms")
    Call<ResultModel<List<Forum>>> getForums(@Query("isFollow") boolean isFollow);

    //获取论坛内容详情
    @GET("myWeb/forum/getFormInfoById")
    Call<ResultModel<Forum>> getForumDetail(@Query("id") long id);

    //*********************************************工具相关******************************************

    //上传文件
    @POST("myWeb/file/upload")
    Call<ResultModel<List<String>>> uploadFiles(@Body RequestBody Body);

}
