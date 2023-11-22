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
import com.example.tan.adapter.StudiedAdapter;
import com.example.tan.databinding.FragmentStudiedBinding;
import com.example.tan.test.Course;


import java.util.ArrayList;
import java.util.List;

public class StudiedFragment extends Fragment {

    private FragmentStudiedBinding mFragmentStudiedBinding;
    public void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mFragmentStudiedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_studied, container, false);

        return mFragmentStudiedBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayListStudied();
    }

    private void displayListStudied() {
        RecyclerView rcvStudied = mFragmentStudiedBinding.rcvStudied;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rcvStudied.setLayoutManager(linearLayoutManager);

        StudiedAdapter studiedAdapter = new StudiedAdapter(getListStudied());
        rcvStudied.setAdapter(studiedAdapter);
    }
    //fetch aip
    private List<Course> getListStudied() {
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