package com.example.tan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tan.R;
import com.example.tan.adapter.CreatedAdapter;
import com.example.tan.databinding.FragmentCreatedBinding;
import com.example.tan.test.Course;

import java.util.ArrayList;
import java.util.List;


public class CreatedFragment extends Fragment {

    private FragmentCreatedBinding mFragmentCreatedBinding;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mFragmentCreatedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_created, container, false);

        return mFragmentCreatedBinding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayListCreated();
    }

    private void displayListCreated() {
        RecyclerView rcvCreated = mFragmentCreatedBinding.rcvCreated;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rcvCreated.setLayoutManager(linearLayoutManager);

        CreatedAdapter createdAdapter = new CreatedAdapter(getListCreated());
        rcvCreated.setAdapter(createdAdapter);
    }

    private List<Course> getListCreated() {
        List<Course> list = new ArrayList<>();
        list.add(new Course("English"));
        list.add(new Course("Vietnamese"));
        list.add(new Course("Spanish"));

        list.add(new Course("English"));
        list.add(new Course("Vietnamese"));
        list.add(new Course("Spanish"));

        list.add(new Course("English"));
        list.add(new Course("Vietnamese"));
        list.add(new Course("Spanish"));

        return list;
    }
}