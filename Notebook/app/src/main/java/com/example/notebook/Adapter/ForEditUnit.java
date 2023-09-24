package com.example.notebook.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.Model.Item_Word;
import com.example.notebook.R;

import java.util.List;

public class ForEditUnit extends RecyclerView.Adapter<ForEditUnit.ViewHolder> {
    List<Item_Word> list_word_Unit;

    public ForEditUnit(List<Item_Word> list_word_Unit) {
        this.list_word_Unit = list_word_Unit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_edit_list_rc, parent, false);
        return new ForEditUnit.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list_word_Unit.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
