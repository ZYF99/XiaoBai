package com.example.myapplication.manager;

import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.login.LoginResultModel;
import com.example.myapplication.model.login.ResetPasswordRequestModel;
import com.example.myapplication.model.login.RegisterRequestModel;
import com.example.myapplication.util.HawkKey;
import com.orhanobut.hawk.Hawk;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    //*********************************************账户相关******************************************
    @FormUrlEncoded
    @POST("myWeb/login")
    Call<ResultModel<LoginResultModel>> login(@Field("email") String email, @Field("password") String password);

    @POST("myWeb/sys/registerUser")
    Call<ResultModel<ResponseBody>> register(@Body RegisterRequestModel registerRequestModel);

    @POST("myWeb/sys/resetPassword")
    Call<ResultModel<ResponseBody>> resetPassword(@Body ResetPasswordRequestModel resetPasswordRequestModel);

    @GET("myWeb/sys/sendVerificationCodeForRegister")
    Call<ResultModel<ResponseBody>> sendEmailCodeForRegister(@Query("email") String email);

    @GET("myWeb/sys/sendVerificationCodeForReset")
    Call<ResultModel<ResponseBody>> sendEmailCodeForResetPassword(@Query("email") String email);

    @GET("myWeb/user/getLoginUserInfo")
    Call<ResultModel<ResponseBody>> getUserInfo();

    @GET("myWeb/user/getRongCloudToken")
    Call<ResultModel<String>> getRongCloudToken();

}
