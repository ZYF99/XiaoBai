package com.example.myapplication.ui.personinfo;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityEditInfoBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.account.EditUserInfoRequestModel;
import com.example.myapplication.model.account.FetchUserInfoResultModel;
import com.example.myapplication.ui.DialogUtil;
import com.example.myapplication.ui.addforum.AddForumActivity;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;

import java.io.File;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

//设置——个人资料界面
public class PersonInfoActivity extends BaseActivity<ActivityEditInfoBinding> {

    FetchUserInfoResultModel userInfoResultModel;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_info;
    }

    @Override
    public void initView(View view) {
        //退出到设置页面
        binding.toolBar.setNavigationOnClickListener(view1 -> finish());

        //修改头像
        binding.llAvatar.setOnClickListener(view12 -> choicePhotoWrapper());

        //修改昵称
        binding.llName.setOnClickListener(view13 -> DialogUtil.showEditNameDialog(PersonInfoActivity.this, userInfoResultModel.getRealName(), newName -> editName(newName)));

        fetchUserInfo();
    }

    //拉取用户信息
    private void fetchUserInfo() {
        ApiUtil.request(RetrofitHelper.getApiService().getUserInfo(),
                new ApiAction<ResultModel<FetchUserInfoResultModel>>() {
                    @Override
                    public void onSuccess(ResultModel<FetchUserInfoResultModel> response) {
                        userInfoResultModel = response.getData();
                        Glide.with(binding.ivAvatar.getContext()).load(response.getData().getAvatar()).into(binding.ivAvatar);
                        binding.tvName.setText(response.getData().getRealName());
                    }
                });
    }

    //选择头像
    @AfterPermissionGranted(AddForumActivity.PRC_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");

            Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                    .maxChooseCount(1) // 图片选择张数的最大值
                    .selectedPhotos(null) // 当前已选中的图片路径集合
                    .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                    .build();
            startActivityForResult(photoPickerIntent, AddForumActivity.PRC_PHOTO_PICKER);
        } else {
            EasyPermissions.requestPermissions(this, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", AddForumActivity.PRC_PHOTO_PICKER, perms);
        }
    }

    //选择图片回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == AddForumActivity.PRC_PHOTO_PICKER) {
            String selectedPhoto = BGAPhotoPickerActivity.getSelectedPhotos(data).get(0);
            Glide.with(binding.ivAvatar.getContext()).load(selectedPhoto).into(binding.ivAvatar);
            uploadAndEditAvatar(selectedPhoto);
        }
    }

    //上传头像并修改
    private void uploadAndEditAvatar(String filePath) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        builder.addFormDataPart(
                "files",
                filePath,
                RequestBody.create(MediaType.parse("image/*"), new File(filePath))
        );
        ApiUtil.request(
                RetrofitHelper.getApiService().uploadFiles(builder.build()),
                new ApiAction<ResultModel<List<String>>>() {
                    @Override
                    public void onSuccess(ResultModel<List<String>> response) {
                        EditUserInfoRequestModel editUserInfoRequestModel = new EditUserInfoRequestModel();
                        editUserInfoRequestModel.setPhotoPath(response.getData().get(0));
                        editUserInfoRequestModel.setId(userInfoResultModel.getId());
                        ApiUtil.request(
                                RetrofitHelper.getApiService().updateUserInfo(editUserInfoRequestModel),
                                new ApiAction<ResultModel<String>>() {
                                    @Override
                                    public void onSuccess(ResultModel<String> response) {
                                        Toast.makeText(PersonInfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                        fetchUserInfo();
                                    }
                                });
                    }
                }
        );
    }

    //修改名称
    private void editName(String newName) {
        EditUserInfoRequestModel editUserInfoRequestModel = new EditUserInfoRequestModel();
        editUserInfoRequestModel.setRealName(newName);
        editUserInfoRequestModel.setId(userInfoResultModel.getId());
        ApiUtil.request(
                RetrofitHelper.getApiService().updateUserInfo(editUserInfoRequestModel),
                new ApiAction<ResultModel<String>>() {
                    @Override
                    public void onSuccess(ResultModel<String> response) {
                        Toast.makeText(PersonInfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        fetchUserInfo();
                    }
                });
    }

}