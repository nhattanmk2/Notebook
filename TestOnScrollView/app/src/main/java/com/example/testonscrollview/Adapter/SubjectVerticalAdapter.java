package com.example.testonscrollview.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonscrollview.Model.Word;
import com.example.testonscrollview.databinding.ItemSubjectRvBinding;
import com.example.testonscrollview.databinding.ItemSubjectVerticalRvBinding;

import java.util.ArrayList;

public class SubjectVerticalAdapter extends RecyclerView.Adapter<SubjectVerticalAdapter.MyViewHolder> {
    ArrayList<Word> arrayList;

    public SubjectVerticalAdapter(ArrayList<Word> arrayList) {
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public SubjectVerticalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSubjectVerticalRvBinding binding = ItemSubjectVerticalRvBinding.inflate(inflater, parent, false);

        return new SubjectVerticalAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectVerticalAdapter.MyViewHolder holder, int position) {
        Word data = arrayList.get(position);
        holder.itemView.cp1Tv1.setText(data.getWord());
        holder.itemView.cp1Tv2.setText(data.getMeaning());
        holder.itemView.cp2Img1.setOnClickListener(v -> {

        });
        holder.itemView.cp2Img2.setOnClickListener(v -> {

        });
    }

    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemSubjectVerticalRvBinding itemView;

        public MyViewHolder(@NonNull ItemSubjectVerticalRvBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }

}
