package com.example.testonscrollview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testonscrollview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(activityMainBinding.getRoot());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        int sizeComponent1 = activityMainBinding.component1.getLayoutParams().height;
        activityMainBinding.scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if (scrollY > 150) {
                updateHeightComponent1(activityMainBinding.component1, 0);
                RunOnUiThread(activityMainBinding.component1, 0, scrollY);
            }
            else if (scrollY < 50) {
                updateHeightComponent1(activityMainBinding.component1, sizeComponent1);
                RunOnUiThread(activityMainBinding.component1, 0, 0);
            }
            else {
                updateHeightComponent1(activityMainBinding.component1, Math.max(sizeComponent1 - scrollY/5, 0));
                RunOnUiThread(activityMainBinding.component1, 0, scrollY/5);
            }
        });
        activityMainBinding.component2.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateHeightComponent1(RelativeLayout component1, int size) {
        ViewGroup.LayoutParams layoutParams = component1.getLayoutParams();
        layoutParams.height = size;
        component1.setLayoutParams(layoutParams);
    }
    public void RunOnUiThread(View v, int x, int y) {
        runOnUiThread(() -> v.scrollTo(x, y));
    }
}