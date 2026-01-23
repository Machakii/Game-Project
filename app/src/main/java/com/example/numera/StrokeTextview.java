package com.example.numera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;

public class StrokeTextview extends androidx.appcompat.widget.AppCompatTextView {

    private int strokeColor = Color.BLACK;
    private float strokeWidth = 7f;

    public StrokeTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        TextPaint paint = getPaint();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(strokeColor);
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getCurrentTextColor());
        super.onDraw(canvas);
    }
}
