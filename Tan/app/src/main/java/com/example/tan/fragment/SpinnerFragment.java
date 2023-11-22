package com.example.tan.fragment;

import android.os.Bundle;
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
import androidx.fragment.app.FragmentTransaction;

import com.example.tan.R;
import com.example.tan.databinding.FragmentSpinnerBinding;


public class SpinnerFragment extends Fragment {

    private FragmentSpinnerBinding mFragmentSpinnerBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        mFragmentSpinnerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_spinner, container, false);

        View view = inflater.inflate(R.layout.fragment_spinner, container, false);



        String [] values =
                {"All Courses","Created","Studied","Downloaded"};
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle the selected item
                String selectedItem = (String) parentView.getItemAtPosition(position);

                // Open a new Fragment based on the selected item
                if ("All Courses".equals(selectedItem)) {
                    // Replace with the appropriate fragment for "All Courses"
                    replaceFragment(new CourseFragment());
                } else if ("Created".equals(selectedItem)) {
                    // Replace with the appropriate fragment for "Created"
                    replaceFragment(new CreatedSpinnerFragment());
                } else if ("Studied".equals(selectedItem)) {
                    // Replace with the appropriate fragment for "Studied"
                    replaceFragment(new StudiedSpinnerFragment());
                } else if ("Downloaded".equals(selectedItem)) {
                    // Replace with the appropriate fragment for "Downloaded"
                    replaceFragment(new DownloadedSpinnerFragment());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });

        return view;
    }

    private void replaceFragment(Fragment newFragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.viewPagerSpinner, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
