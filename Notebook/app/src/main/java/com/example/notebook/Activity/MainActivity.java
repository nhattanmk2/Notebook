package com.example.notebook.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.notebook.Adapter.MainAViewPager;
import com.example.notebook.Effect.DepthPageTransformer;
import com.example.notebook.Fragment.EditUnit;
import com.example.notebook.Fragment.ListFavourite;
import com.example.notebook.Fragment.ListWord;
import com.example.notebook.Fragment.MenuUnit;
import com.example.notebook.Interface.ViewPagerInteractionListener;
import com.example.notebook.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements ViewPagerInteractionListener {
    DrawerLayout drawerLayout;
    FloatingActionButton fab;
    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager2;
    MainAViewPager adapterViewPager;
    ArrayList<Fragment> arrayList = new ArrayList<>();

    private ViewPagerInteractionListener interactionListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);
        CreateFragment();
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.none);
        menuItem.setEnabled(false);

        bottomNavigationView.setBackground(null);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });


    }
    //Chèn phương thức Interface cho MainActivity
    @Override
    public void navigateToFragment(int position, Bundle args) {

        // Lấy Fragment tại vị trí đã cho từ danh sách fragmentList
        Fragment fragment = arrayList.get(position);
        fragment.setArguments(args);
        arrayList.set(position, fragment);
        adapterViewPager.notifyItemChanged(position);
        viewPager2.setCurrentItem(position);
    }
    public void CreateFragment() {
        viewPager2 = findViewById(R.id.viewPager);
        arrayList.add(new ListWord());
        arrayList.add(new ListFavourite());
        arrayList.add(new EditUnit());
        arrayList.add(new MenuUnit());
        adapterViewPager = new MainAViewPager(this, arrayList);
        viewPager2.setAdapter(adapterViewPager);
        FragmentOn();
    }
    public void FragmentOn() {
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.home);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.favourite);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.edit);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.menu);
                        break;
                }

                super.onPageSelected(position);
            }
        });

        viewPager2.setPageTransformer(new DepthPageTransformer());
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    viewPager2.setCurrentItem(0);
                } else if (itemId == R.id.favourite) {
                    viewPager2.setCurrentItem(1);
                } else if (itemId == R.id.edit) {
                    viewPager2.setCurrentItem(2);
                } else if (itemId == R.id.menu) {
                    viewPager2.setCurrentItem(3);
                }
                return true;
            }
        });
    }
    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        LinearLayout shortsLayout = dialog.findViewById(R.id.layoutShorts);
        LinearLayout liveLayout = dialog.findViewById(R.id.layoutLive);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        cancelButton.setImageResource(R.drawable.baseline_clear_24);
        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Upload a Video is clicked", Toast.LENGTH_SHORT).show();

            }
        });

        shortsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Create a short is Clicked", Toast.LENGTH_SHORT).show();

            }
        });

        liveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Go live is Clicked", Toast.LENGTH_SHORT).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}