package com.xs.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-05-17 11:37
 * @email Xs.lin@foxmail.com
 */
public class MyCustomViewActivity extends AppCompatActivity {
    private static final String TAG = "MyCustomViewActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }
}
