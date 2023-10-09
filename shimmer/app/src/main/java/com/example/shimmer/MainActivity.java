package com.example.shimmer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ScrollView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ShimmerFrameLayout shimmerFrameLayout;
    RecyclerView recyclerView;
    recyclerViewAdapter adapter;

    Button button;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList.add("111");
        arrayList.add("222");
        arrayList.add("333");
        arrayList.add("444");
        arrayList.add("444");
        arrayList.add("444");
        arrayList.add("444");
        arrayList.add("444");
        arrayList.add("444");
        arrayList.add("444");
        arrayList.add("444");


//        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER); // ?
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        CustomItemDecoration itemDecoration = new CustomItemDecoration(this, recyclerView);
        recyclerView.addItemDecoration(itemDecoration);
        adapter = new recyclerViewAdapter(arrayList);
        recyclerView.setAdapter(adapter);

//        button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.item_rc);
//
//            }
//        });

        ///Test khi thêm phần tử vào recyclerView
        final Handler handler = new Handler();
        Runnable runnable;
        boolean isRepeating = true;

        runnable = new Runnable() {
            @Override
            public void run() {
                if (isRepeating) {
                    arrayList.add("444");
                    arrayList.add("444");
                    arrayList.add("444");
                    adapter.notifyDataSetChanged();

                    handler.postDelayed(this, 5000);
                }
            }
        };

//        handler.postDelayed(runnable, 5000);
    }
}