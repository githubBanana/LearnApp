package com.xs.retrofitlib.api;

import android.content.Context;
import android.view.View;

import rx.Subscriber;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-04-27 10:19
 * @email Xs.lin@foxmail.com
 */
public abstract class MySubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private View mView;

    public MySubscriber(Context context) {
        mContext = context;
    }

    public MySubscriber(View view) {
        mView = view;
    }

    public MySubscriber() {
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();

        if (mContext != null) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (mView != null) {
//            Snackbar.make(mView, e.getMessage(), Snackbar.LENGTH_SHORT).show();
        }
    }
}
