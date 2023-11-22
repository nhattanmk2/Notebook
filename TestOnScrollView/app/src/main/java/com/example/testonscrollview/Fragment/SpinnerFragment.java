package com.example.testonscrollview.Fragment;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.testonscrollview.Adapter.AllCourseAdapter;
import com.example.testonscrollview.Adapter.NotStudiedAdapter;
import com.example.testonscrollview.Adapter.StudiedAdapter;

import com.example.testonscrollview.Model.Subject;
import com.example.testonscrollview.R;
import com.example.testonscrollview.databinding.FragmentSpinnerBinding;

import java.util.ArrayList;
import java.util.List;


public class SpinnerFragment extends Fragment {

    private FragmentSpinnerBinding mFragmentSpinnerBinding;
//    ArrayList<Subject> allCourse = new ArrayList<>();
    AllCourseAdapter  allCourseAdapter;
    StudiedAdapter studiedAdapter;
    NotStudiedAdapter notStudiedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        mFragmentSpinnerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_spinner, container, false);

        return mFragmentSpinnerBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String [] values =
                {"All Courses","Created","Studied"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mFragmentSpinnerBinding.spinner.setAdapter(adapter);

        addViewForAllCourse();
        addViewForStudied();
        addViewForNotStudied();

        mFragmentSpinnerBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle the selected item
                String selectedItem = (String) parentView.getItemAtPosition(position);

                // Open a new Fragment based on the selected item
                if ("All Courses".equals(selectedItem)) {
                    mFragmentSpinnerBinding.allCourse.setVisibility(View.VISIBLE);
                    mFragmentSpinnerBinding.learn.setVisibility(View.INVISIBLE);
                    mFragmentSpinnerBinding.unlearn.setVisibility(View.INVISIBLE);

                } else if ("Created".equals(selectedItem)) {
                    mFragmentSpinnerBinding.allCourse.setVisibility(View.INVISIBLE);
                    mFragmentSpinnerBinding.learn.setVisibility(View.VISIBLE);
                    mFragmentSpinnerBinding.unlearn.setVisibility(View.INVISIBLE);

                } else if ("Studied".equals(selectedItem)) {
                    mFragmentSpinnerBinding.allCourse.setVisibility(View.INVISIBLE);
                    mFragmentSpinnerBinding.learn.setVisibility(View.INVISIBLE);
                    mFragmentSpinnerBinding.unlearn.setVisibility(View.VISIBLE);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

    }


    private void addViewForNotStudied() {
        notStudiedAdapter = new NotStudiedAdapter(requireContext(), getListCourse());
        mFragmentSpinnerBinding.unlearn.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));
        mFragmentSpinnerBinding.unlearn.setAdapter(notStudiedAdapter);
    }

    private void addViewForStudied() {
        studiedAdapter = new StudiedAdapter(requireContext(), getListCourse());
        mFragmentSpinnerBinding.learn.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false));
        mFragmentSpinnerBinding.learn.setAdapter(studiedAdapter);
    }

    private void addViewForAllCourse() {
        allCourseAdapter = new AllCourseAdapter(requireContext(), getListCourse());
        mFragmentSpinnerBinding.allCourse.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        mFragmentSpinnerBinding.allCourse.setAdapter(allCourseAdapter);
    }

    private List<Subject> getListCourse() {
        List<Subject> list = new ArrayList<>();
        list.add(new Subject("English"));
        list.add(new Subject("Vietnamese"));
        list.add(new Subject("Spanish"));

        list.add(new Subject("English"));
        list.add(new Subject("Vietnamese"));
        list.add(new Subject("Spanish"));


        return list;
    }


}
