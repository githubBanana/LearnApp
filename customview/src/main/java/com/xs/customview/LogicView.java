package com.xs.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-05-17 15:56
 * @email Xs.lin@foxmail.com
 */
public class LogicView extends MyBaseView {
    private static final String TAG = "LogicView";

    private Paint paint;
    private float xScroll;
    private int radiu;
    private boolean running = false;

    public LogicView(Context context) {
        super(context);
        initPaint();
    }

    public LogicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.LogicView);
        running = ta.getBoolean(R.styleable.LogicView_running,false);
        ta.recycle();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(0xff00ff00);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(30);
    }

    @Override
    protected void drawSub(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radiu, paint);
        canvas.drawText("mybaseview", xScroll, 30, paint);
    }

    @Override
    protected void logic() {
        if (running) {
            xScroll++;
            radiu++;
            if (xScroll > getWidth())
                xScroll = -paint.measureText("mybaseview");
            if (radiu > 360)
                radiu = 0;
        }
    }
}
