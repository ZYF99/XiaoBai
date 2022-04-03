package com.example.myapplication.ui.adapter;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemMessageBinding;
import com.example.myapplication.model.Message;

public class MessageRecyclerAdapter extends BaseRecyclerAdapter<Message, ItemMessageBinding> {

    @Override
    int getLayoutRes() {
        return R.layout.item_message;
    }

    @Override
    public void bindData(ItemMessageBinding binding, int position) {
        Message message = baseList.get(position);
        binding.setMessage(message);
    }

}
