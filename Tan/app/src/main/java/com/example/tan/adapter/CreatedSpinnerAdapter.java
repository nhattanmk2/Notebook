package com.example.tan.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tan.R;
import com.example.tan.databinding.ItemCreatedBinding;
import com.example.tan.test.Course;


import java.util.List;

public class CreatedSpinnerAdapter extends RecyclerView.Adapter<CreatedSpinnerAdapter.CreatedSpinnerViewHolder>{

    private List<Course> mListCreatedSpinner;

    public CreatedSpinnerAdapter(List<Course> mListCreatedSpinner) {
        this.mListCreatedSpinner = mListCreatedSpinner;
    }

    @NonNull
    @Override
    public CreatedSpinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCreatedBinding itemCreatedSpinnerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_created, parent, false);
        return new CreatedSpinnerViewHolder(itemCreatedSpinnerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CreatedSpinnerViewHolder holder, int position) {
        Course createdViewModel = mListCreatedSpinner.get(position);
        if (createdViewModel==null){
            return;
        }

        if (holder.itemCreatedSpinnerBinding != null) {
            holder.itemCreatedSpinnerBinding.setCourse(createdViewModel);
        }

    }

    @Override
    public int getItemCount() {
        if (mListCreatedSpinner != null){
            return mListCreatedSpinner.size();
        }
        return 0;
    }

    public static class CreatedSpinnerViewHolder extends RecyclerView.ViewHolder{

        public ItemCreatedBinding itemCreatedSpinnerBinding;

        public CreatedSpinnerViewHolder (@NonNull ItemCreatedBinding itemCreatedSpinnerBinding) {
            super(itemCreatedSpinnerBinding.getRoot());
        }
    }
}