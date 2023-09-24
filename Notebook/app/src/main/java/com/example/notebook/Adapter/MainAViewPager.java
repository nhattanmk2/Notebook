package com.example.notebook.Adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.notebook.Interface.ViewPagerInteractionListener;

import java.util.ArrayList;

public class MainAViewPager extends FragmentStateAdapter {
    ArrayList<Fragment> arrayList;
    int count = 0;
    //Khởi tạo biến cho Interface;
    private ViewPagerInteractionListener interactionListener;
    public MainAViewPager(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> arrayList) {
        super(fragmentActivity);
        this.arrayList = arrayList;
        //Interface
        this.interactionListener = (ViewPagerInteractionListener) fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = arrayList.get(position);
        Log.d("34", "createFragment: " + fragment.getArguments());
        fragment.setArguments(fragment.getArguments());
        return fragment;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
