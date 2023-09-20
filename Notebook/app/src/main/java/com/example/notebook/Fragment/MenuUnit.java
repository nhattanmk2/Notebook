package com.example.notebook.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notebook.Adapter.ForMenuUnit;
import com.example.notebook.Interface.ViewPagerInteractionListener;
import com.example.notebook.Model.Item_Header;
import com.example.notebook.R;

import java.util.ArrayList;
import java.util.List;


public class MenuUnit extends Fragment {
    private ViewPagerInteractionListener interactionListener;
    RecyclerView recyclerView;
    ForMenuUnit forMenuUnit;
    LinearLayout linearLayout;
    List<Item_Header> item_headers = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_unit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        //Khởi tạo Interface cho 1 Fragment
        if (getActivity() instanceof ViewPagerInteractionListener) {
            interactionListener = (ViewPagerInteractionListener) getActivity();
        }

        linearLayout = view.findViewById(R.id.createProject);
        recyclerView = view.findViewById(R.id.menuRC);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        forMenuUnit = new ForMenuUnit(item_headers);
        forMenuUnit.setParentView((ViewGroup) recyclerView.getParent());
        forMenuUnit.setOnLongItemClickListener(new ForMenuUnit.OnLongClickListener() {
            @Override
            public void onItemClick(Item_Header item_header) {
                // Create a PopupWindow
                PopupWindow popupWindow = new PopupWindow(requireContext());
                // Chặn không cho lướt fragment
                if (forMenuUnit.getParentView() != null) {

                    forMenuUnit.getParentView().requestDisallowInterceptTouchEvent(true);
                }
// Set the content view of the PopupWindow to your custom layout
                View customView = LayoutInflater.from(requireContext()).inflate(R.layout.popup_menu, null);
                popupWindow.setContentView(customView);
                TextView textView = customView.findViewById(R.id.TitlePopup);
                textView.setText("Do you want to choose " + item_header.getTitle() + "?");
                ImageView imageView = customView.findViewById(R.id.accept);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle args = new Bundle();
                        args.putString("key", item_header.getAuthor());

                        interactionListener.navigateToFragment(2, args);
                        popupWindow.dismiss();
                    }
                });
                ImageView imageView2 = customView.findViewById(R.id.decline);
                imageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();

                        LayoutInflater inflater = getLayoutInflater();
                        View toastView = inflater.inflate(R.layout.toast_custom, null);

                        // Create and show custom toast
                        Toast customToast = new Toast(MenuUnit.this.getContext());
                        customToast.setDuration(Toast.LENGTH_SHORT);
                        customToast.setView(toastView);
                        customToast.show();
                    }
                });
// Set the dimensions and other settings for the PopupWindow
                popupWindow.setWidth(700);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);

// Apply the dimming effect to the background
                ViewGroup rootView = (ViewGroup) getActivity().getWindow().getDecorView().getRootView();
                Drawable dimmingDrawable = new ColorDrawable(Color.parseColor("#80000000")); // Dimming color
                dimmingDrawable.setBounds(0, 0, rootView.getWidth(), rootView.getHeight());
                dimmingDrawable.setAlpha(180); // Adjust the alpha value as per your preference
                rootView.getOverlay().add(dimmingDrawable);

// Set an OnDismissListener to remove the dimming effect when the popup is dismissed
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        recyclerView.setNestedScrollingEnabled(true);
                        rootView.getOverlay().remove(dimmingDrawable);
                    }
                });

// Show the PopupWindow at the desired location
                popupWindow.showAtLocation(recyclerView, Gravity.CENTER, 0, 0);

            }
        });
        recyclerView.setAdapter(forMenuUnit);
        forMenuUnit.notifyDataSetChanged();
        dataInitialize();
        createNewProject();
    }

    public void createNewProject() {
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                PopupWindow popupWindow = new PopupWindow(requireContext());

// Set the content view of the PopupWindow to your custom layout
                View customView = LayoutInflater.from(requireContext()).inflate(R.layout.create_project, null);
                popupWindow.setContentView(customView);

                popupWindow.setWidth(900);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int screenHeight = displayMetrics.heightPixels;

// Tính toán chiều cao cần thiết (70% của chiều cao màn hình)
                int popupHeight = (int) (screenHeight * 0.5);

// Set chiều cao cho PopupWindow
                popupWindow.setHeight(popupHeight);
                popupWindow.setFocusable(true);

                ViewGroup rootView = (ViewGroup) getActivity().getWindow().getDecorView().getRootView();
                Drawable dimmingDrawable = new ColorDrawable(Color.parseColor("#80000000")); // Dimming color
                dimmingDrawable.setBounds(0, 0, rootView.getWidth(), rootView.getHeight());
                dimmingDrawable.setAlpha(180); // Adjust the alpha value as per your preference
                rootView.getOverlay().add(dimmingDrawable);

                EditText project_name = customView.findViewById(R.id.project_name);
                EditText creator_name = customView.findViewById(R.id.creator_name);

                Button btn = customView.findViewById(R.id.submit);
                Button btnback = customView.findViewById(R.id.back);
                ImageView imageView = customView.findViewById(R.id.image_label);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (project_name.getText().toString().isEmpty()) {
                            project_name.setHint("Please fill project name");
                            project_name.setHintTextColor(getResources().getColor(R.color.rez));
                        }  if (creator_name.getText().toString().isEmpty()) {
                            creator_name.setHint("Please fill creator name");
                            creator_name.setHintTextColor(getResources().getColor(R.color.rez));
                        }  if (!project_name.getText().toString().isEmpty() && !creator_name.getText().toString().isEmpty()) {
                            Log.d("12", "onClick: " + creator_name.getText().toString().isEmpty());
                            Item_Header itemHeader = new Item_Header(project_name.getText().toString(), creator_name.getText().toString(), 15);
                            item_headers.add(itemHeader);
                            Toast.makeText(linearLayout.getContext(), "This is a toast message", Toast.LENGTH_SHORT).show();
                            popupWindow.dismiss();
                        }
                    }
                });
                btnback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(linearLayout.getContext(), "This is a toast message", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });

                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        rootView.getOverlay().remove(dimmingDrawable);
                    }
                });

// Show the PopupWindow at the desired location
                popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
            }
        });
    }

    public void dataInitialize() {
        Item_Header itemHeader1 = new Item_Header("Animal", "Thanh Son", 4);
        Item_Header itemHeader2 = new Item_Header("Animal", "Mai Linh", 4);
        Item_Header itemHeader3 = new Item_Header("Animal", "Mai Hanh", 4);
        Item_Header itemHeader4 = new Item_Header("Animal", "Mai Thuy", 4);
        Item_Header itemHeader5 = new Item_Header("Animal", "Vinh Ngo", 4);
        Item_Header itemHeader6 = new Item_Header("Animal", "Be Son", 4);
        Item_Header itemHeader7 = new Item_Header("Animal", "Be Linh", 4);
        item_headers.add(itemHeader1);
        item_headers.add(itemHeader2);
        item_headers.add(itemHeader3);
        item_headers.add(itemHeader4);
        item_headers.add(itemHeader5);
        item_headers.add(itemHeader6);
        item_headers.add(itemHeader7);
    }
}