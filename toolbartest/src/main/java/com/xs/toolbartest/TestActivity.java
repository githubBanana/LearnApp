package com.xs.toolbartest;

import android.database.Observable;
import android.os.Bundle;
import android.widget.Toast;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-04-26 10:30
 * @email Xs.lin@foxmail.com
 */
public class TestActivity extends ToolBarActivity {
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        setCenterTitle("hio");
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_test_layout;
    }

    @Override
    protected void releaseToolbar() {
        runOnUiThread(() -> Toast.makeText(TestActivity.this, "kaka", Toast.LENGTH_LONG).show());
    }
}
