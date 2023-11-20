package com.example.testonscrollview.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonscrollview.Model.Notification;
import com.example.testonscrollview.databinding.ItemNotificationRvBinding;


import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    ArrayList<Notification> arrayList;
    Context context;

    public NotificationAdapter(Context context, ArrayList<Notification> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemNotificationRvBinding binding = ItemNotificationRvBinding.inflate(inflater, parent, false);

        return new NotificationAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.MyViewHolder holder, int position) {
        Notification data = arrayList.get(position);
        holder.itemView.notification.setText(data.getTitle().trim());
//        holder.itemView.icon.setImageResource();
    }

    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemNotificationRvBinding itemView;

        public MyViewHolder(@NonNull ItemNotificationRvBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
    public void delete(int position) {
//        Toast.makeText(context, "Remove" + position, Toast.LENGTH_SHORT).show();
        arrayList.remove(position);
        notifyItemRemoved(position);
    }
}
