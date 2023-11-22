package com.example.testonscrollview.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.testonscrollview.Fragment.FolderFragment;
import com.example.testonscrollview.Fragment.SpinnerFragment;


//Tan
public class CustomFrgAdapter extends FragmentStateAdapter {

    public CustomFrgAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new FolderFragment();
        }
        return new SpinnerFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
