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

public class StudiedSpinnerAdapter extends RecyclerView.Adapter<StudiedSpinnerAdapter.StudiedSpinnerViewHolder>{

    private List<Course> mListStudiedSpinner;

    public StudiedSpinnerAdapter(List<Course> mListStudiedSpinner) {
        this.mListStudiedSpinner = mListStudiedSpinner;
    }

    @NonNull
    @Override
    public StudiedSpinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCreatedBinding itemStudiedSpinnerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_created, parent, false);
        return new StudiedSpinnerViewHolder(itemStudiedSpinnerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StudiedSpinnerViewHolder holder, int position) {
        Course createdViewModel = mListStudiedSpinner.get(position);

        if (createdViewModel == null) {
            return;
        }

        if (holder.itemStudiedSpinnerBinding != null) {
            holder.itemStudiedSpinnerBinding.setCourse(createdViewModel);
        }

    }

    @Override
    public int getItemCount() {
        if (mListStudiedSpinner != null){
            return mListStudiedSpinner.size();
        }
        return 0;
    }

    public static class StudiedSpinnerViewHolder extends RecyclerView.ViewHolder{

        private ItemCreatedBinding itemStudiedSpinnerBinding;

        public StudiedSpinnerViewHolder (@NonNull ItemCreatedBinding itemStudiedSpinnerBinding) {
            super(itemStudiedSpinnerBinding.getRoot());
        }
    }
}