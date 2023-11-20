package com.example.testonscrollview.Effect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonscrollview.R;

public class BottomLineItemDecoration extends RecyclerView.ItemDecoration {
    private Paint paint;
    private int highlightColor;
    private int lineHeight;
    private int position;

    Context context;

    public BottomLineItemDecoration(int highlightColor, int lineHeight, int position, Context context) {
        this.highlightColor = highlightColor;
        this.lineHeight = lineHeight;
        this.context = context;
        this.position = position;
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(context,R.color.blue));
        paint.setAlpha(255);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineHeight);

    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        int childCount = layoutManager.getChildCount();


        for (int i = 0; i < childCount; i++) {
            View child = layoutManager.getChildAt(i);

            if ( shouldHighlightBottomLine(child, parent)) {

                float startX = child.getLeft();
                float stopX = child.getRight();
                float y = child.getBottom() - 5;

                c.drawLine(startX + 48, y, stopX - 48, y, paint);
                parent.invalidate();
                super.onDrawOver(c, parent, state);
            }
        }
    }


    public void setAlpha(int alpha, RecyclerView recyclerView) {
        paint.setAlpha(alpha);
        recyclerView.invalidate();
    }

    private boolean shouldHighlightBottomLine(View child, RecyclerView parent) {

        int position = parent.getChildAdapterPosition(child);

        RecyclerView.Adapter adapter = parent.getAdapter();
        return adapter != null && position == this.position;
    }
}