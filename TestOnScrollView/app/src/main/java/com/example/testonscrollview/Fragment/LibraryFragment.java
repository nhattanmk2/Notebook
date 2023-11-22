package com.example.testonscrollview.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testonscrollview.Activity.CreateSubject;
import com.example.testonscrollview.Adapter.CustomFrgAdapter;

import com.example.testonscrollview.Effect.DepthPageTransformer;
import com.example.testonscrollview.R;
import com.example.testonscrollview.databinding.FragmentLibraryBinding;
import com.google.android.material.tabs.TabLayout;


public class LibraryFragment extends Fragment {

    FragmentLibraryBinding libraryBinding;
    CustomFrgAdapter adapter;
    private int tabNowSelected;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        libraryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_library, container, false);
        Bundle bundle = getArguments();
        if (bundle != null ) {
            tabNowSelected = bundle.getInt("LibraryFragment");
        }
        setArguments(null);
        return libraryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        libraryBinding.btnadd.setOnClickListener(v -> {
            if (libraryBinding.tabLayoutTest.getSelectedTabPosition() == 0) {
                Intent createSubject = new Intent(requireContext(), CreateSubject.class);
                createSubject.putExtra("Title", "Tạo học phần");
                createSubject.putExtra("TypeCreate", 1);
                requireContext().startActivity(createSubject);

            } else {
                openCreateFolderDialog();
            }
        });
        adapter = new CustomFrgAdapter(requireActivity().getSupportFragmentManager(), requireActivity().getLifecycle());

        libraryBinding.viewPagerTest.setAdapter(adapter);

        libraryBinding.viewPagerTest.setPageTransformer(new DepthPageTransformer());


        libraryBinding.tabLayoutTest.addTab(libraryBinding.tabLayoutTest.newTab().setText("Course"));

        libraryBinding.tabLayoutTest.addTab(libraryBinding.tabLayoutTest.newTab().setText("Folder"));

        libraryBinding.tabLayoutTest.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                libraryBinding.viewPagerTest.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        libraryBinding.viewPagerTest.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                libraryBinding.tabLayoutTest.selectTab(libraryBinding.tabLayoutTest.getTabAt(position));
            }
        });

    }

    private void openCreateFolderDialog() {

    }

}