package com.example.testonscrollview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonscrollview.Model.Folder;
import com.example.testonscrollview.databinding.ItemFolderRvBinding;

import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.MyViewHolder>{
    private static int widthRecycler;
    ArrayList<Folder> arrayList;
    Context context;
    public FolderAdapter(Context context, ArrayList<Folder> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemFolderRvBinding binding = ItemFolderRvBinding.inflate(inflater, parent, false);
        widthRecycler = parent.getWidth();
        return new FolderAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderAdapter.MyViewHolder holder, int position) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        holder.itemView.getRoot().setOnClickListener(v -> {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
        ViewGroup.LayoutParams layoutParams = holder.itemView.getRoot().getLayoutParams();
        layoutParams.width = (int) (widthRecycler * 0.8);

        if (position == 0 && layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = (int) (widthRecycler * 0.075);
            marginLayoutParams.rightMargin = 20;
        }

        if (position != 0 && position != arrayList.size() - 1 && layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = 20;
            marginLayoutParams.rightMargin = 20;
        }

        if (position == arrayList.size() - 1 && layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = 20;
            marginLayoutParams.rightMargin = (int) (widthRecycler * 0.075);
        }
        Folder data = arrayList.get(position);

        holder.itemView.nameFolder.setText(data.getFolder_name());

        holder.itemView.describe.setText(data.getFolder_describe());


        holder.itemView.getRoot().setOnClickListener(v -> {

        });
    }
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemFolderRvBinding itemView;
        public MyViewHolder(@NonNull ItemFolderRvBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}