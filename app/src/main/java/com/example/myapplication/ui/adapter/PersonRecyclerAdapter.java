package com.example.myapplication.ui.adapter;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemPersonBinding;
import com.example.myapplication.model.Person;

public class PersonRecyclerAdapter extends BaseRecyclerAdapter<Person, ItemPersonBinding> {

    @Override
    int getLayoutRes() {
        return R.layout.item_person;
    }

    @Override
    public void bindData(ItemPersonBinding binding, int position) {
        Person person = baseList.get(position);
        binding.setPerson(person);
    }

}