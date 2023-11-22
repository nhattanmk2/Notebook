package com.example.tan.test;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tan.R;
import com.example.tan.adapter.CustomFrgAdapter;
import com.example.tan.databinding.ActivityMainBinding;
import com.example.tan.databinding.DialogCreateFolderBinding;
import com.example.tan.fragment.ClassroomFragment;
import com.example.tan.fragment.SpinnerFragment;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(mainBinding.getRoot());
        mainBinding.btnadd.setOnClickListener(view -> {
            if (mainBinding.tabLayoutTest.getSelectedTabPosition() == 0) {
                Intent goClassroom = new Intent(this, AddToFolderActivity.class);
                startActivity(goClassroom);
            } else {
                openCreateFolderDialog();
            }
        });


        CustomFrgAdapter adapter = new CustomFrgAdapter(getSupportFragmentManager());
        adapter.addTab(new SpinnerFragment(), "Course");
        adapter.addTab(new ClassroomFragment(), "Folder");
        mainBinding.viewPagerTest.setAdapter(adapter);

        mainBinding.tabLayoutTest.setupWithViewPager(mainBinding.viewPagerTest);

    }

    private void openCreateFolderDialog() {
        Dialog dialog = new Dialog(this);
        DialogCreateFolderBinding binding = DialogCreateFolderBinding.inflate(LayoutInflater.from(this));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);


        binding.accept.setOnClickListener(v -> {

//            if (valid1 && valid2) {
//
//                LocalDate currentDate = LocalDate.now();
//
//                LocalDateTime currentTime = LocalDateTime.now();
//
//                DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
//
//                DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//                String formattedDate = currentDate.format(formatterDate);
//
//                String formattedTime = currentTime.format(formatterTime);
//                if (homeFragment instanceof UpdateHomeFragment) {
//                    UpdateHomeFragment myInterface = (UpdateHomeFragment) homeFragment;
//                    if (myInterface != null) {
//                        myInterface.onNewFolder(new Folder(binding.c2.getText().toString().trim(),
//                                binding.c3.getText().toString().trim(), formattedDate, formattedTime));
//                    }
//                }
//
//
//
//                dialog.dismiss();
//            }

        });
        binding.cancel.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
