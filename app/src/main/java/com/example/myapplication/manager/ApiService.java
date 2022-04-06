package com.example.myapplication.manager;

import com.example.myapplication.model.Message;
import com.example.myapplication.model.account.EditUserInfoRequestModel;
import com.example.myapplication.model.forum.AddCommentRequestModel;
import com.example.myapplication.model.forum.AddForumRequestModel;
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

    //关注或取消关注用户
    @GET("myWeb/userRelation/addLoginUserRelation")
    Call<ResultModel<String>> followOrCancelUser(@Query("userId") Long userId);

    //修改用户信息
    @POST("myWeb/user/updateUserInfo")
    Call<ResultModel<String>> updateUserInfo(@Body EditUserInfoRequestModel editUserInfoRequestModel);

    //*********************************************论坛相关******************************************

    //获取论坛列表
    @GET("myWeb/forum/getLoginUserForums")
    Call<ResultModel<List<Forum>>> getForums(@Query("type") String type);

    //获取论坛内容详情
    @GET("myWeb/forum/getForumInfoById")
    Call<ResultModel<Forum>> getForumDetail(@Query("forumId") long id);

    //发布论坛
    @POST("myWeb/forum/addLoginUserForums")
    Call<ResultModel<ResponseBody>> releaseForum(@Body AddForumRequestModel addForumRequestModel);

    //点赞或收藏 type 1:点赞  2：收藏
    @GET("myWeb/forum/praiseOrCollection")
    Call<ResultModel<String>> praiseOrCollection(
            @Query("forumId") long forumId,
            @Query("type") int type
    );

    //发表评论
    @POST("myWeb/forum/addComment")
    Call<ResultModel<String>> addComment(@Body AddCommentRequestModel addCommentRequestModel);

    //*********************************************消息相关******************************************

    //获取点赞和收藏的消息列表
    @GET("myWeb/note/getLoginUserPraiseOrCollection")
    Call<ResultModel<List<Message>>> getLoginUserPraiseOrCollectionList();

    //获取关注的消息列表
    @GET("myWeb/note/getLoginUserFollow")
    Call<ResultModel<List<Message>>> getLoginUserFollowList();

    //获取评论的消息列表
    @GET("myWeb/note/getLoginUserComment")
    Call<ResultModel<List<Message>>> getLoginUserCommentList();

    //*********************************************工具相关******************************************

    //上传文件
    @POST("myWeb/file/upload")
    Call<ResultModel<List<String>>> uploadFiles(@Body RequestBody Body);

}
