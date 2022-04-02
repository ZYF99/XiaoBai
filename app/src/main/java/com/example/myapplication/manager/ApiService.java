package com.example.myapplication.manager;

import com.example.myapplication.model.Forum;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.account.FetchFansAndFocusResultModel;
import com.example.myapplication.model.account.FetchUserInfoResultModel;
import com.example.myapplication.model.login.LoginResultModel;
import com.example.myapplication.model.login.ResetPasswordRequestModel;
import com.example.myapplication.model.login.RegisterRequestModel;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
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

    @GET("myWeb/userRelation/getLoginUserRelation")
    Call<ResultModel<FetchFansAndFocusResultModel>> fetchFansAndFollows(@Query("realName") String realName);

    //*********************************************论坛相关******************************************
    @GET("myWeb/forum/getLoginUserForms")
    Call<ResultModel<List<Forum>>> getForums(
            @Query("isFollow") boolean isFollow
    );

    @GET("myWeb/forum/getFormInfoById")
    Call<ResultModel<Forum>> getForumDetail(
            @Query("id") long id
    );

    //*********************************************工具相关******************************************
    @POST("myWeb/file/upload")
    Call<ResultModel<List<String>>> uploadFiles(@Body RequestBody Body);

}
