package com.example.notebook.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item_title_rv_fragment_list_word, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String data = childData.get(position).getId();
        holder.textView.setText(data);
    }

    @Override
    public int getItemCount() {
        return childData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

}
