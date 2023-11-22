package com.example.tan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tan.R;
import com.example.tan.adapter.CreatedSpinnerAdapter;
import com.example.tan.databinding.FragmentCreatedSpinnerBinding;
import com.example.tan.test.Course;


import java.util.ArrayList;
import java.util.List;

public class CreatedSpinnerFragment extends Fragment {

    private FragmentCreatedSpinnerBinding mFragmentCreatedSpinnerBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentCreatedSpinnerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_created_spinner, container, false);
        displayListCreatedSpinner();
        return mFragmentCreatedSpinnerBinding.getRoot();

    }

    private void displayListCreatedSpinner() {
        RecyclerView rcvCreatedSpinner = mFragmentCreatedSpinnerBinding.rcvCreatedSpinner;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rcvCreatedSpinner.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        rcvCreatedSpinner.addItemDecoration(itemDecoration);

        CreatedSpinnerAdapter createdAdapter = new CreatedSpinnerAdapter(getListCreatedSpinner());
        rcvCreatedSpinner.setAdapter(createdAdapter);
    }

    private List<Course> getListCreatedSpinner() {
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
