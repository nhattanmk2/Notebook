package com.example.testonscrollview.Activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.app.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;


import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;

import android.view.Window;
import android.view.WindowManager;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


import com.example.testonscrollview.Fragment.HomeFragment;
import com.example.testonscrollview.Fragment.LibraryFragment;
import com.example.testonscrollview.Fragment.SearchFragment;
import com.example.testonscrollview.Interface.PutSubject;
import com.example.testonscrollview.Interface.ReplaceFragment;
import com.example.testonscrollview.Interface.UpdateHomeFragment;
import com.example.testonscrollview.Model.Folder;
import com.example.testonscrollview.Model.Notification;
import com.example.testonscrollview.Model.Subject;
import com.example.testonscrollview.Model.Word;
import com.example.testonscrollview.R;
import com.example.testonscrollview.databinding.ActivityMainBinding;
import com.example.testonscrollview.databinding.DialogCreateBinding;
import com.example.testonscrollview.databinding.DialogCreateFolderBinding;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ReplaceFragment, PutSubject {

    ActivityMainBinding activityMainBinding;
    InputMethodManager imm;
    Fragment homeFragment = new HomeFragment(), libraryFragment = new LibraryFragment();
    int previousBtm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(activityMainBinding.getRoot());

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        Intent intent = getIntent();
        load(intent);
        ((HomeFragment) homeFragment).setReplaceFragment(this);
        replaceFragment(homeFragment);

        activityMainBinding.bottomNV.setBackground(null);
        activityMainBinding.bottomNV.getMenu().findItem(R.id.add).setTitle(null);
        activityMainBinding.bottomNV.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.homeMenu) {
                replaceFragment(homeFragment);
            }
            else if (item.getItemId() == R.id.searchMenu){

            }
            else if (item.getItemId() == R.id.add) {
                previousBtm = activityMainBinding.bottomNV.getSelectedItemId();
                openCreateDialog();
            }
            else if (item.getItemId() == R.id.libraryMenu) {
                replaceFragment(libraryFragment);
            }
            else if (item.getItemId() == R.id.profileMenu)
                Toast.makeText(getApplicationContext(), "HH4", Toast.LENGTH_SHORT).show();

            return true;
        });
    }
    //TEST
    private void load(Intent intent) {
        Subject sbj = (Subject) intent.getSerializableExtra("Subject");
        Bundle bundle = new Bundle();
        bundle.putSerializable("Subject", sbj);
        homeFragment.setArguments(bundle);
    }

    @Override
    public void replace(Fragment fragment, int state) {
        if (state == 11) {
            //search fragment
            replaceFragment(fragment);
            ((SearchFragment) fragment).setReplaceFragment(this);
        }
        if (state == 1) {
            //home fragment
            replaceFragment(fragment);
            ((HomeFragment) fragment).setReplaceFragment(this);
        }
        if (state == 30) {

            activityMainBinding.bottomNV.setSelectedItemId(R.id.libraryMenu);
        }
        if (state == 31) {

            activityMainBinding.bottomNV.setSelectedItemId(R.id.libraryMenu);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MainView, fragment);
        fragmentTransaction.commit();
    }
    private void openCreateDialog() {
        Dialog dialog = new Dialog(this);
        DialogCreateBinding binding = DialogCreateBinding.inflate(LayoutInflater.from(this));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.SlideUpAnimation);

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.BOTTOM;
        window.setAttributes(windowAttributes);

        binding.createSubject.setOnClickListener(v -> {
            Intent createSubject = new Intent(this, CreateSubject.class);
            createSubject.putExtra("Title", "Tạo học phần");
            createSubject.putExtra("TypeCreate", 1);
            startActivity(createSubject);
            dialog.dismiss();
        });

        binding.createFolder.setOnClickListener(v -> {
            openCreateFolderDialog();
            dialog.dismiss();

        });

        dialog.setOnDismissListener(dialog1 -> activityMainBinding.bottomNV.setSelectedItemId(previousBtm));
        dialog.show();
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
        window.setWindowAnimations(R.style.SlideUpAnimation);

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        binding.c2.postDelayed(() -> {
            binding.c2.requestFocus();
            imm.showSoftInput(binding.c2, 0);
        }, 100);

        binding.accept.setOnClickListener(v -> {
            boolean valid1 = checkValidNameFolder(binding.c2);
            boolean valid2 = checkValidDescribe(binding.c3);
            if (valid1 && valid2) {

                LocalDate currentDate = LocalDate.now();

                LocalDateTime currentTime = LocalDateTime.now();

                DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");

                DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                String formattedDate = currentDate.format(formatterDate);

                String formattedTime = currentTime.format(formatterTime);
                if (homeFragment instanceof UpdateHomeFragment) {
                    UpdateHomeFragment myInterface = (UpdateHomeFragment) homeFragment;
                    if (myInterface != null) {
                        myInterface.onNewFolder(new Folder(binding.c2.getText().toString().trim(),
                        binding.c3.getText().toString().trim(), formattedDate, formattedTime));
                    }
                }

//                Log.d("99", "openCreateFolderDialog: " + formattedDate + " " + formattedTime);

                dialog.dismiss();
            }
        });

        binding.cancel.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
    private boolean checkValidDescribe(EditText c3) {
        if (c3.getText().toString().length() == 0) {
            c3.setHint("Bạn cần nhập mô tả cho thư mục");
            c3.postDelayed(() -> {
                c3.setHint(R.string.item_folder_3);
            }, 2000);
            return false;
        }
        return true;
    }

    private boolean checkValidNameFolder(EditText c2) {
        if (c2.getText().toString().length() == 0) {
            c2.setHint("Bạn cần nhập tên cho thư mục");
            c2.postDelayed(() -> {
                c2.setHint(R.string.item_folder_1);
            }, 2000);
            return false;
        }
        return true;
    }

    @Override
    public void put(ArrayList<Word> arr) {

    }
}