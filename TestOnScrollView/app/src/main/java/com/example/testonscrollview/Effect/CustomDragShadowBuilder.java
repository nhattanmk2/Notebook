package com.example.testonscrollview.Effect;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

class CustomDragShadowBuilder extends View.DragShadowBuilder {
    Paint shadowPaint;
    public CustomDragShadowBuilder(View view, int shadowColor) {
        super(view);
        shadowPaint = new Paint();
        shadowPaint.setColor(shadowColor);
    }

    public void setShadowColor(int shadowColor) {
        shadowPaint.setColor(shadowColor);
        getView().invalidate();
    }

    @Override
    public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {

        super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);
    }
    @Override
    public void onDrawShadow(Canvas canvas) {
        canvas.drawRect(0, 0, getView().getWidth(), getView().getHeight(), shadowPaint);
        super.onDrawShadow(canvas);
    }
}
