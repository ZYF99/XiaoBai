package com.example.myapplication.ui.adapter;

import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemMessageBinding;
import com.example.myapplication.model.Message;

public class MessageRecyclerAdapter extends BaseRecyclerAdapter<Message, ItemMessageBinding> {

    OnItemLongClickListener onItemLongClickListener;

    @Override
    int getLayoutRes() {
        return R.layout.item_message;
    }

    @Override
    public void bindData(ItemMessageBinding binding, int position) {
        Message message = baseList.get(position);
        binding.setMessage(message);
        binding.getRoot().setOnLongClickListener(view -> {
            onItemLongClickListener.onLongClick(message);
            return false;
        });
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemLongClickListener{
        public void onLongClick(Message message);
    }

}
