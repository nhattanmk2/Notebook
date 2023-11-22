package com.example.tan.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tan.R;
import com.example.tan.adapter.CourseAdapter;
import com.example.tan.test.Course;


import java.util.ArrayList;
import java.util.List;

public class CourseFragment extends Fragment {

    private RecyclerView rcvCourse;

    private CourseAdapter courseAdapter;
    SearchView searchView;
    public CourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        // Inflate the layout for this fragment

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvCourse = view.findViewById(R.id.rcv_course);
        courseAdapter = new CourseAdapter(getContext());
        searchView = view.findViewById(R.id.sv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCourse.setLayoutManager(linearLayoutManager);

        courseAdapter.setData(getListCourse());
        rcvCourse.setAdapter(courseAdapter);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(requireContext(), "111", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                courseAdapter.getFilter().filter(newText);

                return false;
            }
        });
    }

    private List<Course> getListCourse() {
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
