package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.adapter.PersonRecyclerAdapter;
import com.example.myapplication.model.Person;
import java.util.ArrayList;
import java.util.List;

//内嵌的人物列表界面
public class InnerPersonFragment extends Fragment {

    String tag;
    RecyclerView rvPerson;
    PersonRecyclerAdapter personRecyclerAdapter;

    public InnerPersonFragment() {

    }

    public InnerPersonFragment(String tag) {
        this.tag = tag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_inner_person, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPerson = view.findViewById(R.id.rv_person);
        personRecyclerAdapter = new PersonRecyclerAdapter(getContext());
        rvPerson.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPerson.setAdapter(personRecyclerAdapter);
        initList();
    }

    private void initList() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("", ""));
        personList.add(new Person("", ""));
        personList.add(new Person("", ""));
        personList.add(new Person("", ""));
        personRecyclerAdapter.replaceList(personList);
    }

}
