package com.example.notebook.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.Model.Item_Favourite;
import com.example.notebook.Model.Item_Word;
import com.example.notebook.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class forFavouriteSearch extends RecyclerView.Adapter<forFavouriteSearch.ViewHolder> implements Filterable  {

    List<Item_Word> listWord;
    List<Item_Word> listWordC;
    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public forFavouriteSearch(List<Item_Word> listWord) {
        this.listWord = listWord;
        this.listWordC = listWord;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_search, parent, false);
        return new forFavouriteSearch.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.word.setText(listWord.get(position).getId().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onItemClick(view, listWord.get(position).getKey());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listWord.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView word;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.textView4);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if (strSearch.isEmpty()){
                    listWord = listWordC;
                }
                else {
                    List<Item_Word> list = new ArrayList<>();
                    for (Item_Word item : listWordC) {
                        if (item.getId().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(item);
                        }
                    }
                    listWord = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listWord;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listWord = (List<Item_Word>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
