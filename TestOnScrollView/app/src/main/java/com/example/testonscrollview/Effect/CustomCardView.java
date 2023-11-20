package com.example.testonscrollview.Effect;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;


import androidx.cardview.widget.CardView;

import com.example.testonscrollview.Interface.GotoLearnActivity;

import com.example.testonscrollview.R;

public class CustomCardView extends CardView {
    int check = 0;
    int state;

    private boolean isClicked = false;
    GotoLearnActivity gotoLearnActivity;
    Paint paint;

    public void setGotoLearnActivity(GotoLearnActivity gotoLearnActivity) {
        this.gotoLearnActivity = gotoLearnActivity;
    }

    public CustomCardView(Context context) {
        super(context);
        init();
    }

    public CustomCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomCardView);
        state = a.getInt(R.styleable.CustomCardView_state, 0);
        a.recycle();
        init();
    }

    public CustomCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        setClickable(true);
        setFocusable(true);
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isClicked) {

            float startX = 0;
            float startY = getHeight() - 5;
            float endX = getWidth();
            float endY = getHeight() - 5;
            canvas.drawLine(startX, startY, endX, endY, paint);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (check > 0) {
                    if (gotoLearnActivity != null) {
                        gotoLearnActivity.onChoose(state);
                    }
                    check = 0;
                } else {
                    isClicked = true;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                check++;
                isClicked = false;
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
}