package com.example.notebook.Adapter;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.Model.Item_Word;
import com.example.notebook.R;

import java.util.List;

public class ChildForTitleRV extends RecyclerView.Adapter<ChildForTitleRV.ViewHolder> {

    List<Item_Word> childData;
    public ChildForTitleRV(List<Item_Word> childData) {
        this.childData = childData;
    }

    public List<Item_Word> getChildData() {
        return childData;
    }

    public void setChildData(List<Item_Word> childData) {
        this.childData = childData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item_title_rv_fragment_list_word, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Item_Word data = childData.get(position);
//        String data = childData.get(position).getId();
//        holder.textView.setText(data.getId());
        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Đã được ưu thích
                if (data.isStatus() == true) {
                    childData.get(position).setStatus(false);

                    holder.favourite.setImageDrawable(holder.itemView.getContext().getDrawable(R.drawable.ic_star));
                    Toast.makeText(holder.itemView.getContext(), "unfavourite", Toast.LENGTH_SHORT).show();
                }
                else {
                    childData.get(position).setStatus(true);

                    holder.favourite.setImageDrawable(holder.itemView.getContext().getDrawable(R.drawable.baseline_dis_favourite_24));
                    Toast.makeText(holder.itemView.getContext(), "Favourite", Toast.LENGTH_SHORT).show();
                }
                setChildData(childData);
            }
        });


    }

    @Override
    public int getItemCount() {
        return childData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView favourite;
        ImageView speaker;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            favourite = itemView.findViewById(R.id.favourite);
            speaker = itemView.findViewById(R.id.speaker);
        }
    }

}
