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

public class StudiedAdapter extends RecyclerView.Adapter<StudiedAdapter.StudiedViewHolder>{

    private List<Course> mListStudied;

    public StudiedAdapter(List<Course> mListStudied) {
        this.mListStudied = mListStudied;
    }

    @NonNull
    @Override
    public StudiedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCreatedBinding itemStudiedBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_created, parent, false);
        return new StudiedViewHolder(itemStudiedBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StudiedViewHolder holder, int position) {
        Course createdViewModel = mListStudied.get(position);

        if (createdViewModel == null){
            return;
        }

        if (holder.itemStudiedBinding != null) {
            holder.itemStudiedBinding.setCourse(createdViewModel);
        }

    }

    @Override
    public int getItemCount() {
        if (mListStudied != null){
            return mListStudied.size();
        }
        return 0;
    }

    public static class StudiedViewHolder extends RecyclerView.ViewHolder{

        private ItemCreatedBinding itemStudiedBinding;

        public StudiedViewHolder (@NonNull ItemCreatedBinding itemStudiedBinding) {
            super(itemStudiedBinding.getRoot());
        }
    }
}