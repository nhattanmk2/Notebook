package com.example.notebook.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.Model.Item_Word;
import com.example.notebook.R;

import java.util.ArrayList;
import java.util.List;

public class ForTitleRV extends RecyclerView.Adapter<ForTitleRV.ViewHolder> {

    List<List<Item_Word>> childData;
    private List<ViewHolder> viewHolderList = new ArrayList<>();
    public ForTitleRV(List<List<Item_Word>> childData) {
        this.childData = childData;

    }
    public List<ViewHolder> getViewHolderList() {
        return viewHolderList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_rv_fragment_list_word, parent, false);
        ViewHolder holder = new ViewHolder(view);
        viewHolderList.add(holder); // Thêm holder vào viewHolderList
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Set Item chủ đề tương đương với Item trong ForTopRV
        List<Item_Word> childItems = childData.get(position);

        holder.textView.setText("Unit");
        holder.childForTitleRV = new ChildForTitleRV(childItems);

        holder.childRecyclerView.setAdapter(holder.childForTitleRV);

//        viewHolderList.add(holder);
        holder.childForTitleRV.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return childData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView childRecyclerView;
        ChildForTitleRV childForTitleRV;
        TextView textView;
        public int item() {
            return childForTitleRV.getItemCount();
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.headerItem);
            childRecyclerView = itemView.findViewById(R.id.itemTitleRecyclerView);
            childRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }

}

