package com.example.testonscrollview.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.testonscrollview.Adapter.SearchAdapter;
import com.example.testonscrollview.Interface.ReplaceFragment;
import com.example.testonscrollview.R;
import com.example.testonscrollview.databinding.FragmentSearchBinding;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    FragmentSearchBinding fragmentSearchBinding;
    InputMethodManager imm;
    ReplaceFragment replaceFragment;
    ArrayList<String> arrayList = new ArrayList<>();
    SearchAdapter searchAdapter;

    public void setReplaceFragment(ReplaceFragment replaceFragment) {
        this.replaceFragment = replaceFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        return fragmentSearchBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addData();
        LoadViewForSearch();
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        fragmentSearchBinding.searchBox.postDelayed(() -> {
            fragmentSearchBinding.searchBox.requestFocus();
            imm.showSoftInput(fragmentSearchBinding.searchBox, 0);
        }, 100);

        fragmentSearchBinding.back.setOnClickListener(v -> {
            if (replaceFragment != null) {
                replaceFragment.replace(new HomeFragment(), 1);
            }
        });

        fragmentSearchBinding.searchBox.setOnTouchListener((v, event) -> {
            final int Right = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (fragmentSearchBinding.searchBox.getText().toString().length() > 0)
                    if (event.getRawX() >= fragmentSearchBinding.searchBox.getRight() - fragmentSearchBinding.searchBox.getCompoundDrawables()[Right].getBounds().width() - 20) {
                        fragmentSearchBinding.searchBox.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
                        fragmentSearchBinding.title1.setVisibility(View.VISIBLE);
                        fragmentSearchBinding.title2.setVisibility(View.VISIBLE);
                        fragmentSearchBinding.searchRV.setVisibility(View.GONE);
                        fragmentSearchBinding.searchBox.setText("");
                        return true;
                    }
            }
            return false;
        });
        fragmentSearchBinding.searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Log.d("6", "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (fragmentSearchBinding.searchBox.getText().toString().length() > 0) {
                    Drawable icon = ContextCompat.getDrawable(requireContext(), R.drawable.close_dialog_notification);
                    fragmentSearchBinding.searchBox.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icon, null);
                    fragmentSearchBinding.title1.setVisibility(View.GONE);
                    fragmentSearchBinding.title2.setVisibility(View.GONE);
                    fragmentSearchBinding.searchRV.setVisibility(View.VISIBLE);
                }
                else {
                    fragmentSearchBinding.searchBox.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
                    fragmentSearchBinding.searchRV.setVisibility(View.GONE);
                    fragmentSearchBinding.title1.setVisibility(View.VISIBLE);
                    fragmentSearchBinding.title2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//                Log.d("7", "afterTextChanged: " + s.toString());
                searchAdapter.getFilter().filter(s.toString());
            }
        });
    }

    private void LoadViewForSearch() {
        searchAdapter = new SearchAdapter(requireContext(), arrayList);
        fragmentSearchBinding.searchRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        fragmentSearchBinding.searchRV.setAdapter(searchAdapter);
    }

    private void addData() {
        arrayList.add("hihi");
        arrayList.add("fffi");
        arrayList.add("hmmi");
        arrayList.add("eei");
        arrayList.add("bbb");
        arrayList.add("aaa");
        arrayList.add("son");
        arrayList.add("thh");
        arrayList.add("vsc");
        arrayList.add("qqq");
        arrayList.add("fffi");
    }
}