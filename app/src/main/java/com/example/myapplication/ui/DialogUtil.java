package com.example.myapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.DialogPersonInfoBinding;
import com.example.myapplication.model.Person;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class DialogUtil {

    public static void showPersonInfoDialog(Context context, Person person, OnPersonInfoItemClickListener onPersonInfoItemClickListener) {
        DialogPersonInfoBinding dialogPersonInfoBinding = DialogPersonInfoBinding.inflate(LayoutInflater.from(context));
        Glide.with(dialogPersonInfoBinding.ivAvatar.getContext()).load(person.getPhotoPath()).placeholder(R.drawable.icon_message).into(dialogPersonInfoBinding.ivAvatar);
        dialogPersonInfoBinding.setPerson(person);
        dialogPersonInfoBinding.btnFollow.setOnClickListener(view ->
                        onPersonInfoItemClickListener.onFollowClick(person)
                //onPersonInfoItemClickListener.onUnFollowClick(person)
        );
        dialogPersonInfoBinding.btnMessage.setOnClickListener(view -> onPersonInfoItemClickListener.onSendMessageClick(person));
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(dialogPersonInfoBinding.getRoot());
        dialog.show();
    }

    public interface OnPersonInfoItemClickListener {
        public void onFollowClick(Person person);

        public void onUnFollowClick(Person person);

        public void onSendMessageClick(Person person);

    }
}
