package com.example.notebook.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.Model.Item_Header;
import com.example.notebook.R;

import java.util.List;

public class ForMenuUnit extends  RecyclerView.Adapter<ForMenuUnit.ViewHolder> {

    private List<Item_Header> item_headers;
    private ViewGroup parentView;

    public ViewGroup getParentView(){
        return this.parentView;
    }
    public void setParentView(ViewGroup parentView) {
        this.parentView = parentView;
    }
    public ForMenuUnit(List<Item_Header> item_headers) {
        this.item_headers = item_headers;
    }
    private OnLongClickListener itemClickListener;
    public void setOnLongItemClickListener(OnLongClickListener listener) {
        this.itemClickListener = listener;
    }
    public interface OnLongClickListener {
        void onItemClick(Item_Header item);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_menu_unit_rc, parent, false);
        return new ForMenuUnit.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item_Header itemHeader = item_headers.get(position);
        holder.text_counts.setText(String.valueOf(itemHeader.getCounts()));
        holder.text_author.setText(itemHeader.getAuthor());
        holder.text_Title.setText(itemHeader.getTitle());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(itemHeader);
                }

                return false;
            }
        } );
    }

    @Override
    public int getItemCount() {
        return item_headers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_author;
        TextView text_Title;
        TextView text_counts;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_author = itemView.findViewById(R.id.text_author);
            text_Title = itemView.findViewById(R.id.text_title);
            text_counts = itemView.findViewById(R.id.text_words);
        }
    }
}
