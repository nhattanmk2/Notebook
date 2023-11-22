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

public class CreatedAdapter extends RecyclerView.Adapter<CreatedAdapter.CreatedViewHolder>{

    private List<Course> mListCreated;

    public CreatedAdapter(List<Course> mListCreated) {
        this.mListCreated = mListCreated;
    }

    @NonNull
    @Override
    public CreatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCreatedBinding itemCreatedBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_created, parent, false);
        return new CreatedViewHolder(itemCreatedBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CreatedViewHolder holder, int position) {
        Course createdViewModel = mListCreated.get(position);

        if (createdViewModel == null) {
            return;
        }

        if (holder.itemCreatedBinding != null) {
            holder.itemCreatedBinding.setCourse(createdViewModel);
        }

    }

    @Override
    public int getItemCount() {
        if (mListCreated != null){
            return mListCreated.size();
        }
        return 0;
    }

    public static class CreatedViewHolder extends RecyclerView.ViewHolder{

        private ItemCreatedBinding itemCreatedBinding;

        public CreatedViewHolder (@NonNull ItemCreatedBinding itemCreatedBinding) {
            super(itemCreatedBinding.getRoot());
        }
    }
}
