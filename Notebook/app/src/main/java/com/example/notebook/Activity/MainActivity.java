package com.example.notebook.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.notebook.Fragment.ListWord;
import com.example.notebook.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateFragment();
    }
    public void CreateFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ListWord()).commit();
    }
}