package com.xs.onepixels;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-08-25 09:29
 * @email Xs.lin@foxmail.com
 */
public class KeepLiveReceiver extends BroadcastReceiver {
    private static final String TAG = "KeepLiveReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e(TAG, "onReceive: "+action );
        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
        } else if (Intent.ACTION_SCREEN_ON.equals(action)) {

        } else if (Intent.ACTION_USER_PRESENT.equals(action)) {

        }
    }
}
