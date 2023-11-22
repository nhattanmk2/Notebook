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
import com.example.tan.adapter.StudiedSpinnerAdapter;
import com.example.tan.databinding.FragmentStudiedSpinnerBinding;
import com.example.tan.test.Course;


import java.util.ArrayList;
import java.util.List;

public class StudiedSpinnerFragment extends Fragment {

    private FragmentStudiedSpinnerBinding mFragmentStudiedSpinnerBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentStudiedSpinnerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_studied_spinner, container, false);
        displayListStudiedSpinner();
        return mFragmentStudiedSpinnerBinding.getRoot();

    }
    private void displayListStudiedSpinner() {
        RecyclerView rcvStudiedSpinner = mFragmentStudiedSpinnerBinding.rcvStudiedSpinner;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rcvStudiedSpinner.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        rcvStudiedSpinner.addItemDecoration(itemDecoration);

        StudiedSpinnerAdapter studiedAdapter = new StudiedSpinnerAdapter(getListStudied());
        rcvStudiedSpinner.setAdapter(studiedAdapter);
    }

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