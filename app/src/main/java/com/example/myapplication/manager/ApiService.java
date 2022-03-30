package com.example.myapplication.manager;

import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.login.ResetPasswordRequestModel;
import com.example.myapplication.model.login.RegisterRequestModel;

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
    Call<ResultModel<String>> login(@Field("username") String userName, @Field("password") String password);

    @POST("myWeb/sys/registerUser")
    Call<ResultModel<ResponseBody>> register(@Body RegisterRequestModel registerRequestModel);

    @POST("myWeb/sys/resetPassword")
    Call<ResultModel<ResponseBody>> resetPassword(@Body ResetPasswordRequestModel resetPasswordRequestModel);

    @GET("myWeb/sys/sendVerificationCodeForRegister")
    Call<ResultModel<ResponseBody>> sendEmailCodeInRegister(@Query("email") String email);

    @GET("myWeb/sys/sendVerificationCodeForRegister")
    Call<ResultModel<ResponseBody>> sendEmailCodeInResetPassword(@Query("email") String email);

    @GET("myWeb/user/getLoginUserInfo")
    Call<ResponseBody> getUserInfo();
}
