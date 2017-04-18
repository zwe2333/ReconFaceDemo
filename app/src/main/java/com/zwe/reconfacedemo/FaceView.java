package com.zwe.reconfacedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Asus on 2017/4/18.
 */

public class FaceView extends ImageView{
    private Paint mPaint;
    private Rect mRect;
    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mRect!=null){
            canvas.drawRect(mRect,mPaint);
        }
    }

    public void drawFace(Rect rect){
        this.mRect=rect;
        invalidate();
    }
}
