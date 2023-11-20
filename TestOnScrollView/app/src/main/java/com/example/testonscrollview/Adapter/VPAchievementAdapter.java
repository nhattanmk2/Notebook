package com.example.testonscrollview.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonscrollview.Interface.GoAchievement;
import com.example.testonscrollview.Model.HomeAchievement;
import com.example.testonscrollview.databinding.ItemFolderRvBinding;
import com.example.testonscrollview.databinding.ItemHomefragmentViewpagerBinding;

import java.util.ArrayList;

public class VPAchievementAdapter extends RecyclerView.Adapter<VPAchievementAdapter.ViewHolder>{
    ArrayList<HomeAchievement> arrayList;
    GoAchievement sendType;
    public VPAchievementAdapter(ArrayList<HomeAchievement> arrayList, GoAchievement sendType) {
        this.arrayList = arrayList;
        this.sendType = sendType;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemHomefragmentViewpagerBinding binding = ItemHomefragmentViewpagerBinding.inflate(inflater, parent, false);

        return new VPAchievementAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeAchievement data = arrayList.get(position);
        holder.itemView.img.setImageResource(data.getImg_id());
        holder.itemView.title.setText(data.getTitle_Achievement());
        holder.itemView.img.setOnClickListener(v -> {
            if (sendType != null) {
                sendType.openAv(data.getType());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemHomefragmentViewpagerBinding itemView;
        public ViewHolder(@NonNull ItemHomefragmentViewpagerBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}

