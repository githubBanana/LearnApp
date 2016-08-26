package com.xs.retrofitlib.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xs.retrofitlib.R;
import com.xs.retrofitlib.model.LabelTopic;

import java.util.List;

import retrofit2.Call;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-06-12 15:05
 * @email Xs.lin@foxmail.com
 */
public class LinShiActivity extends AppCompatActivity{
    private static final String TAG = "LinShiActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linshi_layout);
        final Button _test = (Button) findViewById(R.id.btn_test);
        _test.setOnClickListener(v -> {
            Call<List<LabelTopic>> ks = RequestTest.getRepo();
        });
    }
}
