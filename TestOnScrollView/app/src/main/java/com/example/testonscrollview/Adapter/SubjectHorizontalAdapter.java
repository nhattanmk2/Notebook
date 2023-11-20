package com.example.testonscrollview.Adapter;

import android.animation.AnimatorSet;
import android.os.Handler;

import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonscrollview.Model.Word;
import com.example.testonscrollview.databinding.ItemSubjectHorizontalBinding;
import com.example.testonscrollview.databinding.ItemSubjectVerticalRvBinding;

import java.util.ArrayList;

public class SubjectHorizontalAdapter extends RecyclerView.Adapter<SubjectHorizontalAdapter.MyViewHolder> {
    ArrayList<Word> arrayList;
    ArrayList<Boolean> stateList;
    private AnimatorSet frontAnim;
    private AnimatorSet backAnim;
    float scale;
    float elevationInPixels;

    public SubjectHorizontalAdapter(ArrayList<Word> arrayList, ArrayList<Boolean> stateList, float scale, AnimatorSet frontAnim, AnimatorSet backAnim, float elevationInPixels) {
        this.arrayList = arrayList;
        this.stateList = stateList;
        this.scale = scale;
        this.frontAnim = frontAnim;
        this.backAnim = backAnim;
        this.elevationInPixels = elevationInPixels;
    }

    @NonNull
    @Override
    public SubjectHorizontalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSubjectHorizontalBinding binding = ItemSubjectHorizontalBinding.inflate(inflater, parent, false);

        return new SubjectHorizontalAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectHorizontalAdapter.MyViewHolder holder, int position) {

        Word data = arrayList.get(position);
        holder.itemView.cardFront.setText(data.getWord());
        holder.itemView.cardBack.setText(data.getMeaning());

        holder.itemView.cardBack.setCameraDistance(8000 * scale);
        holder.itemView.cardFront.setCameraDistance(8000 * scale);

        holder.itemView.cardFront.setOnClickListener(v -> {
            boolean state = stateList.get(position);

            holder.itemView.cardBack.setElevation(0);
            holder.itemView.cardFront.setElevation(0);

            new Handler().postDelayed(() -> {
                holder.itemView.cardBack.setElevation(elevationInPixels);
                holder.itemView.cardFront.setElevation(elevationInPixels);
            }, 700);

            stateList.set(position, !state);

            if (state) {
                frontAnim.setTarget(holder.itemView.cardFront);
                backAnim.setTarget(holder.itemView.cardBack);
                frontAnim.start();
                backAnim.start();
            } else {
                frontAnim.setTarget(holder.itemView.cardBack);
                backAnim.setTarget(holder.itemView.cardFront);
                backAnim.start();
                frontAnim.start();
            }
        });
    }

    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemSubjectHorizontalBinding itemView;

        public MyViewHolder(@NonNull ItemSubjectHorizontalBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }


}

