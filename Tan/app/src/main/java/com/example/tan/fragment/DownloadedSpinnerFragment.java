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
import com.example.tan.adapter.DownloadedSpinnerAdapter;
import com.example.tan.databinding.FragmentDownloadedSpinnerBinding;
import com.example.tan.test.Course;


import java.util.ArrayList;
import java.util.List;

public class DownloadedSpinnerFragment extends Fragment {

    private FragmentDownloadedSpinnerBinding mFragmentDownloadedSpinnerBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentDownloadedSpinnerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_downloaded_spinner, container, false);
        displayListDownloadedSpinner();
        return mFragmentDownloadedSpinnerBinding.getRoot();

    }

    private void displayListDownloadedSpinner() {
        RecyclerView rcvDownloadedSpinner = mFragmentDownloadedSpinnerBinding.rcvDownloadedSpinner;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rcvDownloadedSpinner.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        rcvDownloadedSpinner.addItemDecoration(itemDecoration);

        DownloadedSpinnerAdapter studiedAdapter = new DownloadedSpinnerAdapter(getListStudied());
        rcvDownloadedSpinner.setAdapter(studiedAdapter);
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