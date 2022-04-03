package com.example.myapplication.manager;

import com.example.myapplication.model.Forum;
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

    //*********************************************账户相关******************************************
    @FormUrlEncoded
    @POST("myWeb/login")
    Call<ResultModel<LoginResultModel>> login(@Field("email") String email, @Field("password") String password);

    @GET("myWeb/user/getLoginUserInfo")
    Call<ResultModel<FetchUserInfoResultModel>> getUserInfo();

    @GET("myWeb/user/getUserInfoById")
    Call<ResultModel<FetchUserInfoResultModel>> getUserInfoById();

    @POST("myWeb/sys/registerUser")
    Call<ResultModel<ResponseBody>> register(@Body RegisterRequestModel registerRequestModel);

    @POST("myWeb/sys/resetPassword")
    Call<ResultModel<ResponseBody>> resetPassword(@Body ResetPasswordRequestModel resetPasswordRequestModel);

    @GET("myWeb/sys/sendVerificationCodeForRegister")
    Call<ResultModel<ResponseBody>> sendEmailCodeForRegister(@Query("email") String email);

    @GET("myWeb/sys/sendVerificationCodeForReset")
    Call<ResultModel<ResponseBody>> sendEmailCodeForResetPassword(@Query("email") String email);

    @GET("myWeb/rongyun/getToken")
    Call<ResultModel<String>> getRongCloudToken(
            @Query("email") String email,
            @Query("id") String id
    );

    //获取粉丝和关注的人
    @GET("myWeb/userRelation/getLoginUserRelation")
    Call<ResultModel<FetchFansAndFocusResultModel>> fetchFansAndFollows(@Query("realName") String realName);

    //关注用户
    @GET("myWeb/userRelation/addLoginUserRelation")
    Call<ResultModel<ResponseBody>> focusUser(@Query("userId") Long userId);

    //*********************************************论坛相关******************************************

    //获取论坛列表，isFollow为true时获取关注的人的论坛
    @GET("myWeb/forum/getLoginUserForms")
    Call<ResultModel<List<Forum>>> getForums(@Query("isFollow") boolean isFollow);

    @GET("myWeb/forum/getFormInfoById")
    Call<ResultModel<Forum>> getForumDetail(
            @Query("id") long id
    );

    //*********************************************工具相关******************************************
    @POST("myWeb/file/upload")
    Call<ResultModel<List<String>>> uploadFiles(@Body RequestBody Body);

}
