package com.example.recyclerviewhorizontal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.os.Handler;

import com.example.recyclerviewhorizontal.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityMainBinding;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(mActivityMainBinding.getRoot());
        addTest();
        recyclerViewAdapter = new RecyclerViewAdapter(arrayList, mActivityMainBinding.recyclerView);

        mActivityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        mActivityMainBinding.recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void addTest() {
        arrayList.add("111");
        arrayList.add("222");
        arrayList.add("333");
        arrayList.add("111");
        arrayList.add("111");
        arrayList.add("111");
        arrayList.add("111");
        arrayList.add("111");
        arrayList.add("222");
        arrayList.add("333");
        arrayList.add("111");
        arrayList.add("111");
        arrayList.add("111");
        arrayList.add("111");

//        Handler handler = new Handler();
//        Runnable runnable;
//
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                    arrayList.add("444");
//                    arrayList.add("444");
//                    arrayList.add("444");
//                    handler.postDelayed(this, 5000);
//
//            }
//        };
//
//        handler.postDelayed(runnable, 5000);
    }
}