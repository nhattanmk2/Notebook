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

public class DownloadedSpinnerAdapter extends RecyclerView.Adapter<DownloadedSpinnerAdapter.DownloadedSpinnerViewHolder>{

    private List<Course> mListDownloadedSpinner;

    public DownloadedSpinnerAdapter(List<Course> mListDownloadedSpinner) {
        this.mListDownloadedSpinner = mListDownloadedSpinner;
    }

    @NonNull
    @Override
    public DownloadedSpinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCreatedBinding itemDownloadedSpinnerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_created, parent, false);
        return new DownloadedSpinnerViewHolder(itemDownloadedSpinnerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadedSpinnerViewHolder holder, int position) {
        Course createdViewModel = mListDownloadedSpinner.get(position);

        if (createdViewModel == null){
            return;
        }

        if (holder.itemDownloadedSpinnerBinding != null){
            holder.itemDownloadedSpinnerBinding.setCourse(createdViewModel);
        }

    }

    @Override
    public int getItemCount() {
        if (mListDownloadedSpinner != null){
            return mListDownloadedSpinner.size();
        }
        return 0;
    }

    public static class DownloadedSpinnerViewHolder extends RecyclerView.ViewHolder{

        private ItemCreatedBinding itemDownloadedSpinnerBinding;

        public DownloadedSpinnerViewHolder (@NonNull ItemCreatedBinding itemDownloadedSpinnerBinding) {
            super(itemDownloadedSpinnerBinding.getRoot());
        }
    }
}