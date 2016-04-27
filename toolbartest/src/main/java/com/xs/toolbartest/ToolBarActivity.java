package com.xs.toolbartest;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-04-26 10:27
 * @email Xs.lin@foxmail.com
 */
public abstract class ToolBarActivity extends BaseActivity {
    private static final String TAG = "ToolBarActivity";

    protected View _conTentView;
    private Toolbar _toolBar;
    private TextView _tvCenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _conTentView = getLayoutInflater().inflate(getContentViewId(),null);
        setContentView(_conTentView);
        _toolBar = (Toolbar) findViewById(R.id.toolbar_id);
        _tvCenter = (TextView) findViewById(R.id.tv_center_Id);
        setSupportActionBar(_toolBar);
        showUpEnabled(true);
    }

    public void showUpEnabled(boolean enable) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        getSupportActionBar().setTitle(titleId);
    }

    public void setCenterTitle(CharSequence centerTitle) {
        setTitle("");
        _tvCenter.setText(centerTitle);
    }

    protected abstract int getContentViewId();
    protected abstract void releaseToolbar();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }
        if (id == R.id.item_release) {
            releaseToolbar();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

}
