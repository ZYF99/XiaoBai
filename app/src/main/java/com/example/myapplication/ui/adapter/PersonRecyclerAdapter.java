package com.example.myapplication.ui.adapter;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemPersonBinding;
import com.example.myapplication.model.Person;

public class PersonRecyclerAdapter extends BaseRecyclerAdapter<Person, ItemPersonBinding> {

    boolean isFollowOther;//是否是"我的关注"界面
    OnInnerItemClickListener onInnerItemClickListener;

    public PersonRecyclerAdapter(boolean isFollowOther) {
        this.isFollowOther = isFollowOther;
    }

    @Override
    int getLayoutRes() {
        return R.layout.item_person;
    }

    @Override
    public void bindData(ItemPersonBinding binding, int position) {
        Person person = baseList.get(position);
        binding.setPerson(person);
        Glide.with(binding.ivAvatar.getContext()).load(person.getAvatar()).placeholder(R.drawable.icon_account).into(binding.ivAvatar);
        //binding.btnUnfollow.setVisibility(isFollowOther ? View.VISIBLE : View.GONE);
        if (person.isFollow()) binding.btnFollowFunction.setText("取消关注");
        else binding.btnFollowFunction.setText("关注");
        binding.ivAvatar.setOnClickListener(view -> onInnerItemClickListener.onAvatarClick(person));
        binding.btnFollowFunction.setOnClickListener(view -> {
            if (person.isFollow()) onInnerItemClickListener.onUnFollowClick(person);
            else onInnerItemClickListener.onFollowClick(person);
        });
    }

    public void setOnInnerItemClickListener(OnInnerItemClickListener onInnerItemClickListener) {
        this.onInnerItemClickListener = onInnerItemClickListener;
    }

    public interface OnInnerItemClickListener {
        void onAvatarClick(Person person);

        void onFollowClick(Person person);

        void onUnFollowClick(Person person);
    }

}