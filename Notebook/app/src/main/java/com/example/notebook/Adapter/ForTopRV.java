package com.example.notebook.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.R;

import java.util.List;

public class ForTopRV extends RecyclerView.Adapter<ForTopRV.ViewHolder> {

    private List<String> headers;
    private List<Boolean> status;
    private RecyclerView TitleRV;
    private boolean touch;

//    private OnItemClickListener listener;

    public ForTopRV(List<String> headers, List<Boolean> status, RecyclerView TitleRV) {
        this.headers = headers;
        this.status = status;
        this.TitleRV = TitleRV;
    }
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }
//    public interface OnItemClickListener {
//        void OnItemClick(int position, RecyclerView TitleRV);
//    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_rv_fragment_list_word, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String header = headers.get(position);
        holder.header_item.setText(header);
        holder.isClicked = status.get(position);
        holder.updateItemAppearance();
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                changeStatus(position);
//                TitleRV.smoothScrollToPosition(position);
//
//                notifyDataSetChanged();
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return headers.size();
    }

    public boolean getTouch() {
        return this.touch;
    }
    public void setTouch(boolean touch) {
        this.touch = touch;
    }
    public int getStatus() {
        for (int i = 0; i < this.status.size(); i++) {
            if (this.status.get(i) == true) return i;
        }
        return 0;
    }
    public void changeStatus(int position) {
        for (int i = 0; i < this.status.size(); i++) {
            this.status.set(i, false);
        }
        this.status.set(position, true);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        //Khai báo các gía trị trong  item_top_rv_fragment_list_word.xml
        TextView header_item;
        boolean isClicked = false;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            header_item = itemView.findViewById(R.id.title);
            header_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();


                    changeStatus(position);
                    TitleRV.smoothScrollToPosition(position);

                    notifyDataSetChanged();
                }
            });

        }
        private void updateItemAppearance() {
            if (isClicked) {
                header_item.setTextColor(Color.DKGRAY);
                header_item.setBackgroundResource(R.drawable.hightlight_item_top_rv_fragment_list_word);
                Typeface customTypeface = Typeface.createFromAsset(header_item.getContext().getAssets(), "font/prompt.ttf");
                header_item.setTypeface(customTypeface);
            } else {
                header_item.setTextColor(Color.BLACK);
                header_item.setBackgroundResource(R.drawable.right_border);
                Typeface customTypeface = Typeface.createFromAsset(header_item.getContext().getAssets(), "font/mukta.ttf");
                header_item.setTypeface(customTypeface);
            }
        }
    }
}
