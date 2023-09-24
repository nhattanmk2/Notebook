package com.example.notebook.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.Interface.DataChangeListener;
import com.example.notebook.Model.Item_Favourite;
import com.example.notebook.Model.Item_Word;
import com.example.notebook.R;
import com.example.notebook.Thread.UpdateDataAsyncTask;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ForFavourite extends RecyclerView.Adapter<ForFavourite.ViewHolder> {
    private List<Item_Favourite> listFavourites;
    private List<Boolean> statusDropdowns;
    private DataChangeListener dataChangeListener;

    public void setDataChangeListener(DataChangeListener listener) {
        this.dataChangeListener = listener;
    }

    public ForFavourite(List<Item_Favourite> listFavourites,  List<Boolean> statusDropdowns) {
       this.listFavourites = listFavourites;
       this.statusDropdowns = statusDropdowns;
    }

    public void setStatus(int position, boolean status) {
        statusDropdowns.set(position, status);
    }

    public List<Item_Favourite> getListFavourites() {
        return this.listFavourites;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_favourite_list_rc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Item_Favourite itemFavourite = listFavourites.get(position);

        holder.title.setText(itemFavourite.getTitle());
        holder.progressBar.setProgress((int) ( itemFavourite.getList_Favourite().size() * 100 /itemFavourite.getCount() ));
        holder.item_Rv_Fa.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.item_Rv_Fa_Adapter = new ChildForFavourite(itemFavourite.getList_Favourite());
        holder.item_Rv_Fa.setAdapter(holder.item_Rv_Fa_Adapter);
//        itemFavourite.setList_Favourite(holder.item_Rv_Fa_Adapter.getItems_child_favourite());
        holder.item_Rv_Fa_Adapter.setDataChangeListener(dataChangeListener);
        UpdateDataAsyncTask refresh = new UpdateDataAsyncTask();
        refresh.execute(holder.item_Rv_Fa_Adapter.getItems_child_favourite());

        if (statusDropdowns.get(position) == false) {
            holder.fabFa.setIcon(ContextCompat.getDrawable(holder.fabFa.getContext(), R.drawable.baseline_arrow_drop_up_24));
            holder.itemView.findViewById(R.id.itemTitleRecyclerViewFa).setVisibility(View.GONE);
        }
        if (statusDropdowns.get(position) == true) {
            holder.fabFa.setIcon(ContextCompat.getDrawable(holder.fabFa.getContext(), R.drawable.baseline_arrow_drop_down_24));
            holder.itemView.findViewById(R.id.itemTitleRecyclerViewFa).setVisibility(View.VISIBLE);
        }
        holder.fabFa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("20", "onBindViewHolder: " + position + " " + itemFavourite.getList_Favourite().size());
                if (statusDropdowns.get(position) == false) {
                    //Danh sách được mở
                    statusDropdowns.set(position, true);
                    holder.fabFa.setIcon(ContextCompat.getDrawable(holder.fabFa.getContext(), R.drawable.baseline_arrow_drop_down_24));
                    holder.itemView.findViewById(R.id.itemTitleRecyclerViewFa).setVisibility(View.VISIBLE);

                }
                else {
                    statusDropdowns.set(position, false);
                    holder.fabFa.setIcon(ContextCompat.getDrawable(holder.fabFa.getContext(), R.drawable.baseline_arrow_drop_up_24));
                    holder.itemView.findViewById(R.id.itemTitleRecyclerViewFa).setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listFavourites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ExtendedFloatingActionButton fabFa;
        ProgressBar progressBar;
        RecyclerView item_Rv_Fa;
        ChildForFavourite item_Rv_Fa_Adapter;
        // Danh sách đang được đóng
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.headerItemFa);
            fabFa = itemView.findViewById(R.id.itemTitleFabFa);
            progressBar = itemView.findViewById(R.id.progressBar);
            item_Rv_Fa = itemView.findViewById(R.id.itemTitleRecyclerViewFa);
        }
    }


}
