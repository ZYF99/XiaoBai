package com.example.myapplication.ui.addforum;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityAddForumBinding;
import com.example.myapplication.manager.RetrofitHelper;
import com.example.myapplication.model.ResultModel;
import com.example.myapplication.model.forum.AddForumRequestModel;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.util.ApiAction;
import com.example.myapplication.util.ApiUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class AddForumActivity extends BaseActivity<ActivityAddForumBinding> implements EasyPermissions.PermissionCallbacks, BGASortableNinePhotoLayout.Delegate {

    public static final int PRC_PHOTO_PICKER = 1;

    private static final int RC_CHOOSE_PHOTO = 1;
    public static final int RC_PHOTO_PREVIEW = 2;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_forum;
    }

    AlertDialog loadingDialog;

    @Override
    public void initView(View view) {
        loadingDialog = new AlertDialog.Builder(AddForumActivity.this)
                .setMessage("发布中...")
                .setCancelable(false)
                .create();

        binding.snplMomentAddPhotos.setMaxItemCount(9);
        binding.snplMomentAddPhotos.setDelegate(this);
        binding.snplMomentAddPhotos.setSortable(true);
        binding.snplMomentAddPhotos.setPlusEnable(true);

        //退出进入我的页面
        binding.toolBar.setNavigationOnClickListener(view1 -> finish());


        //发布
        binding.tvMomentAddPublish.setOnClickListener(view1 -> {
            loadingDialog.show();
            String content = binding.etMomentAddContent.getText().toString().trim();
            if (content.length() == 0 && binding.snplMomentAddPhotos.getItemCount() == 0) {
                Toast.makeText(AddForumActivity.this, "必须填写这一刻的想法或选择照片！", Toast.LENGTH_SHORT).show();
                return;
            }

            if (binding.snplMomentAddPhotos.getItemCount() == 0) {
                release(binding.etMomentAddContent.getText().toString().trim(), null);
            } else {
                uploadFiles();
            }
        });
    }

    //上传图片
    private void uploadFiles() {
        List<String> picList = binding.snplMomentAddPhotos.getData();

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        for (String s : picList) {
            File file = new File(s);
            builder.addFormDataPart(
                    "files",
                    file.getName(),
                    RequestBody.create(MediaType.parse("image/*"), file)
            );
        }
        ApiUtil.request(
                RetrofitHelper.getApiService().uploadFiles(builder.build()),
                new ApiAction<ResultModel<List<String>>>() {
                    @Override
                    public void onSuccess(ResultModel<List<String>> response) {
                        release(binding.etMomentAddContent.getText().toString().trim(), response.getData());
                    }
                }
        );
    }

    //发布论坛
    private void release(String content, @Nullable List<String> files) {
        String listJson;
        if (files == null) listJson = "";
        else listJson = files.toString();
        ApiUtil.request(
                RetrofitHelper.getApiService().releaseForum(
                        new AddForumRequestModel(content, listJson)
                ),
                new ApiAction<ResultModel<ResponseBody>>() {
                    @Override
                    public void onSuccess(ResultModel<ResponseBody> response) {
                        Toast.makeText(AddForumActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                        loadingDialog.dismiss();
                        finish();
                    }

                    @Override
                    public void onFailed(Throwable t) {
                        super.onFailed(t);
                        loadingDialog.dismiss();
                    }
                }
        );
    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        choicePhotoWrapper();
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        binding.snplMomentAddPhotos.removeItem(position);
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(binding.snplMomentAddPhotos.getMaxItemCount()) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        startActivityForResult(photoPickerPreviewIntent, RC_PHOTO_PREVIEW);
    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {
        Toast.makeText(this, "排序发生变化", Toast.LENGTH_SHORT).show();
    }

    @AfterPermissionGranted(PRC_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            /*// 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");*/

            Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                    .cameraFileDir(null) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                    .maxChooseCount(binding.snplMomentAddPhotos.getMaxItemCount() - binding.snplMomentAddPhotos.getItemCount()) // 图片选择张数的最大值
                    .selectedPhotos(null) // 当前已选中的图片路径集合
                    .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                    .build();
            startActivityForResult(photoPickerIntent, RC_CHOOSE_PHOTO);
        } else {
            EasyPermissions.requestPermissions(this, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", PRC_PHOTO_PICKER, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == PRC_PHOTO_PICKER) {
            Toast.makeText(this, "您拒绝了「图片选择」所需要的相关权限!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO) {
            binding.snplMomentAddPhotos.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));

        } else if (requestCode == RC_PHOTO_PREVIEW) {
            binding.snplMomentAddPhotos.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
        }
    }


}
