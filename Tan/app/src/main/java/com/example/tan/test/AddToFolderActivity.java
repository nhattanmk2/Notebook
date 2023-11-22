package com.example.tan.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.example.tan.R;
import com.example.tan.adapter.AddFolderFrgAdapter;
import com.example.tan.databinding.ActivityAddToFolderBinding;
import com.example.tan.fragment.CreatedFragment;
import com.example.tan.fragment.StudiedFragment;

import com.google.android.material.tabs.TabLayout;

public class AddToFolderActivity extends AppCompatActivity {

    private ActivityAddToFolderBinding addToFolderBinding;


    //Thiếu button đồng ý thay đổi -> Interface 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addToFolderBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_to_folder);
        setContentView(addToFolderBinding.getRoot());
        AddFolderFrgAdapter addToFolderAdapter = new AddFolderFrgAdapter(getSupportFragmentManager());
        addToFolderAdapter.addTab(new CreatedFragment(),"Created");
        addToFolderAdapter.addTab(new StudiedFragment(),"Studied");
        addToFolderBinding.viewPagerAdd.setAdapter(addToFolderAdapter);
        addToFolderBinding.tabLayoutAdd.setupWithViewPager(addToFolderBinding.viewPagerAdd);

    }

}