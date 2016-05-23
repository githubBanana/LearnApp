package com.xs.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-05-17 11:36
 * @email Xs.lin@foxmail.com
 */
public abstract class MyBaseView extends View {
    private static final String TAG = "MyBaseView";

    private MyThread myThread = null;
    private boolean running = true;
    public MyBaseView(Context context) {
        super(context);
        initPaint();
    }

    public MyBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint(){}
    @Override
    protected final void onDraw(Canvas canvas) {

        if (myThread == null) {
            myThread = new MyThread();
            myThread.start();
        } else {
            drawSub(canvas);
        }

    }

    protected abstract void drawSub(Canvas canvas);
    protected abstract void logic();


    class MyThread extends Thread {
        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logic();
                postInvalidate();

            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        running = false;
        super.onDetachedFromWindow();
    }
}
