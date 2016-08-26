package com.xs.learnapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xs.customview.demo1.MyCustomViewActivity;
import com.xs.learnapp.adapter.MainListAdapter;
import com.xs.retrofitlib.NetTestActivity;
import com.xs.toolbartest.TestActivity;
import com.xs.viewpagertest.activity.VFActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_black));
        setSupportActionBar(toolbar);
        showUpEnabled(true);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        init();

    }
    public void showUpEnabled(boolean enable) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    /***************************************** start here *******************************************************/
    private ListView mListView;
    private MainListAdapter mMainListAdapter;
    private void init() {
        mListView = (ListView) findViewById(R.id.mainListViewId);
        mMainListAdapter = new MainListAdapter(this,getList());
        mListView.setAdapter(mMainListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(MainActivity.this, VFActivity.class);
                        startActivity(intent);

                        break;
                    case 1:
                        intent.setClass(MainActivity.this, TestActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(MainActivity.this, NetTestActivity.class);
                        startActivity(intent);

                        break;
                    case 3:
                        intent.setClass(MainActivity.this, MyCustomViewActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        break;
                }
            }
        });

    }
    private List<String> getList() {
        List<String> list = new ArrayList<>();
        for (String s :getResources().getStringArray(R.array.learn_array)) {
            list.add(s);
        }
        return  list;
    }
}
