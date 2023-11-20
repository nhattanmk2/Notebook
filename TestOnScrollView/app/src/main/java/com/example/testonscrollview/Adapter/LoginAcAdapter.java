package com.example.testonscrollview.Adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.testonscrollview.Model.HomeAchievement;
import com.example.testonscrollview.databinding.ItemAchievementDetailBinding;

import java.util.ArrayList;

public class LoginAcAdapter extends RecyclerView.Adapter<LoginAcAdapter.MyViewHolder> {

    ArrayList<HomeAchievement> arrayList;

    public LoginAcAdapter(ArrayList<HomeAchievement> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public LoginAcAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAchievementDetailBinding binding = ItemAchievementDetailBinding.inflate(inflater, parent, false);

        return new LoginAcAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LoginAcAdapter.MyViewHolder holder, int position) {
        HomeAchievement data = arrayList.get(position);
        holder.itemView.img.setImageResource(data.getImg_id());
        holder.itemView.title.setText(data.getTitle_Achievement());
    }

    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemAchievementDetailBinding itemView;

        public MyViewHolder(@NonNull ItemAchievementDetailBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
