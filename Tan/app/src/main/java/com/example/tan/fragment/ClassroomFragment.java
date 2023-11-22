package com.example.tan.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.tan.R;
import com.example.tan.adapter.ClassroomAdapter;
import com.example.tan.viewmodel.Classroom;


import java.util.ArrayList;
import java.util.List;

public class ClassroomFragment extends Fragment {
    private RecyclerView rcvClassroom;

    private ClassroomAdapter classroomAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classroom, container, false);
        // Inflate the layout for this fragment
        rcvClassroom = view.findViewById(R.id.rcv_classroom);
        classroomAdapter = new ClassroomAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvClassroom.setLayoutManager(linearLayoutManager);

        classroomAdapter.setData(getListClassroom());
        rcvClassroom.setAdapter(classroomAdapter);

        return view;
    }

    private List<Classroom> getListClassroom() {
        List<Classroom> list = new ArrayList<>();
        list.add(new Classroom("English","2 courses"));
        list.add(new Classroom("Vietnamese", "3 courses"));
        list.add(new Classroom("Spanish","4 courses"));

        list.add(new Classroom("English","2 courses"));
        list.add(new Classroom("Vietnamese", "3 courses"));
        list.add(new Classroom("Spanish","4 courses"));

        list.add(new Classroom("English","2 courses"));
        list.add(new Classroom("Vietnamese", "3 courses"));
        list.add(new Classroom("Spanish","4 courses"));

        list.add(new Classroom("English","2 courses"));
        list.add(new Classroom("Vietnamese", "3 courses"));
        list.add(new Classroom("Spanish","4 courses"));

        return list;
    }
}