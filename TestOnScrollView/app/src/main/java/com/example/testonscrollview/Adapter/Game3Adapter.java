package com.example.testonscrollview.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonscrollview.R;

import java.util.ArrayList;

public class Game3Adapter extends RecyclerView.Adapter<Game3Adapter.ViewHolder>  {

    ArrayList<Character> list1;
    public Game3Adapter(ArrayList<Character> list1) {
        this.list1 = list1;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game3, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(String.valueOf(list1.get(position)));
    }

    @Override
    public int getItemCount() {
        return list1 != null ? list1.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv1);
        }
    }

    public ArrayList<Character> getList1() {
        return list1;
    }

    public void moveItem(int oldPos, int newPos) {
        Character item = list1.get(oldPos);
        list1.remove(oldPos);
        list1.add(newPos, item);
        notifyItemMoved(oldPos, newPos);
    }

}
