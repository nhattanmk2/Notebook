package com.example.testonscrollview.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonscrollview.databinding.ItemSearchRvBinding;


import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> implements Filterable {
    ArrayList<String> arrayList;
    ArrayList<String> arrayListOld;
    Context context;

    public SearchAdapter(Context context, ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        this.arrayListOld = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSearchRvBinding binding = ItemSearchRvBinding.inflate(inflater, parent, false);
        return new SearchAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {
        String data = arrayList.get(position);
        holder.itemView.search2.setText(data);
    }

    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemSearchRvBinding itemView;

        public MyViewHolder(@NonNull ItemSearchRvBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String keySearch = constraint.toString();
//                Log.d("7", "performFiltering: " + keySearch);
                //Query theo tên nếu word -> subject -> folder
                if (keySearch.isEmpty()) {
                    arrayList = arrayListOld;
                }
                else {
                    ArrayList<String> res = new ArrayList<>();
                    for (String i : arrayListOld) {
                        if (i.contains(keySearch.toLowerCase())) {
                            res.add(i);
                        }
                    }
                    arrayList = res;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrayList = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
