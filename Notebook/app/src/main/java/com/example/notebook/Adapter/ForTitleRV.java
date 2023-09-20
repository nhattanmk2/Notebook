package com.example.notebook.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.Model.Item_Word;
import com.example.notebook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ForTitleRV extends RecyclerView.Adapter<ForTitleRV.ViewHolder> {

    List<List<Item_Word>> childData;
    List<Boolean> actionFab = new ArrayList<>();
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
        viewHolderList.add(holder);
        actionFab.add(false);
        // Thêm holder vào viewHolderList
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //Set Item chủ đề tương đương với Item trong ForTopRV
        List<Item_Word> childItems = childData.get(position);

        holder.textView.setText("Unit");
        holder.childForTitleRV = new ChildForTitleRV(childItems);

        holder.childRecyclerView.setAdapter(holder.childForTitleRV);
        //Thay đổi dữ liệu từ con
        childData.set(position, holder.childForTitleRV.getChildData());
        for (int i = 0; i < childData.get(position).size(); ++ i) {
            Log.d("22", "onBindViewHolder: " + position + " " + i + " " + childData.get(position).get(i).isStatus());
        }
        holder.childForTitleRV.notifyDataSetChanged();
        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (actionFab.get(position) != null) {
                    actionFab.set(position, !actionFab.get(position));
                    if (actionFab.get(position)) {
                        holder.fab.setImageResource(R.drawable.baseline_arrow_drop_up_24);
                        holder.itemView.findViewById(R.id.itemTitleRecyclerView).setVisibility(View.GONE);
                    }
                    else {
                        holder.fab.setImageResource(R.drawable.baseline_arrow_drop_down_24);
                        holder.itemView.findViewById(R.id.itemTitleRecyclerView).setVisibility(View.VISIBLE);
                    }
//                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return childData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView childRecyclerView;
        ChildForTitleRV childForTitleRV;
        TextView textView;
        FloatingActionButton fab;
        public int item() {
            return childForTitleRV.getItemCount();
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.headerItem);
            childRecyclerView = itemView.findViewById(R.id.itemTitleRecyclerView);
            fab = itemView.findViewById(R.id.itemTitleFab);
            childRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }

}

