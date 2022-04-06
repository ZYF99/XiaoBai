package com.example.myapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.DialogEditNameBinding;
import com.example.myapplication.databinding.DialogPersonInfoBinding;
import com.example.myapplication.model.Person;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class DialogUtil {

    //弹出个人信息弹窗
    public static void showPersonInfoDialog(Context context, Person person, boolean hasFollow, OnPersonInfoItemClickListener onPersonInfoItemClickListener) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        DialogPersonInfoBinding dialogPersonInfoBinding = DialogPersonInfoBinding.inflate(LayoutInflater.from(context));
        dialogPersonInfoBinding.btnFollow.setText(hasFollow ? "取消关注" : "关注");
        Glide.with(dialogPersonInfoBinding.ivAvatar.getContext()).load(person.getPhotoPath()).placeholder(R.drawable.icon_message).into(dialogPersonInfoBinding.ivAvatar);
        dialogPersonInfoBinding.setPerson(person);
        dialogPersonInfoBinding.btnFollow.setOnClickListener(view ->
                {
                    onPersonInfoItemClickListener.onFollowClick(person);
                    dialog.dismiss();
                }
        );
        dialogPersonInfoBinding.btnMessage.setOnClickListener(view -> onPersonInfoItemClickListener.onSendMessageClick(person));

        dialog.setContentView(dialogPersonInfoBinding.getRoot());
        dialog.show();
    }

    public interface OnPersonInfoItemClickListener {
        public void onFollowClick(Person person);

        public void onUnFollowClick(Person person);

        public void onSendMessageClick(Person person);

    }

    //弹出修改名称弹窗
    public static void showEditNameDialog(Context context, String oldName, OnEditNameClickListener onEditNameClickListener) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        DialogEditNameBinding dialogEditNameBinding = DialogEditNameBinding.inflate(LayoutInflater.from(context));
        dialogEditNameBinding.etName.setText(oldName);
        dialogEditNameBinding.btnConfirm.setOnClickListener(view -> {
                    String newName = dialogEditNameBinding.etName.getText().toString();
                    if (!newName.isEmpty()) {
                        onEditNameClickListener.onConfirmClick(newName);
                        dialog.dismiss();
                    }
                }

        );

        dialog.setContentView(dialogEditNameBinding.getRoot());
        dialog.show();
    }

    public interface OnEditNameClickListener {
        public void onConfirmClick(String newName);
    }

}
