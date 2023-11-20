package com.example.testonscrollview.Effect;

import android.content.ClipData;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.testonscrollview.Interface.State;
import com.example.testonscrollview.R;


public class CustomTextView extends AppCompatTextView {

    CustomDragShadowBuilder shadowBuilder;
    State st;
    ClipData data;
    int state;
    int shadowColor = Color.RED;
    private boolean flipState;
    public void setSt(State st) {
        this.st = st;
    }
    public void setFlipState(boolean state) {this.flipState = state;}

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        state = typedArray.getInt(R.styleable.CustomTextView_textState, -1);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setClickable(true);
        setFocusable(true);
    }
    public void remove() {

        this.cancelDragAndDrop();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (state != 0 && flipState) {

                    data = ClipData.newPlainText("", "");
                    shadowColor = Color.TRANSPARENT;
                    shadowBuilder = new CustomDragShadowBuilder(this, shadowColor);
                    this.startDragAndDrop(data, shadowBuilder, this, 0);
                    if (st != null) {
                        st.upState(true, this, flipState);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:

                if (st != null) {
                    st.upState(false, this, flipState);
                }
                break;

        }
        return super.onTouchEvent(event);
    }

}