package com.example.recyclerviewhorizontal;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class CustomItemDecoration extends RecyclerView.ItemDecoration {
    private int spacing;
    private int targetItemPosition;

    public CustomItemDecoration(int spacing, int targetItemPosition) {
        this.spacing = spacing;
        this.targetItemPosition = targetItemPosition;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position == targetItemPosition) {
            outRect.top = spacing;
            outRect.bottom = spacing;
        }

    }
}