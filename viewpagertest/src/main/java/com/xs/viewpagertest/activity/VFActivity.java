package com.xs.viewpagertest.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.xs.viewpagertest.R;
import com.xs.viewpagertest.adapter.MyAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-04-23 11:47
 * @email Xs.lin@foxmail.com
 */
public class VFActivity extends AppCompatActivity {

    private static final String TAG = "VFActivity";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vf_activity_layout);
        initView();
    }

    private void initView() {
        mTabLayout  = (TabLayout) findViewById(R.id.tablayoutId);
        mTabLayout.addTab(mTabLayout.newTab().setText("YY"));
        mTabLayout.addTab(mTabLayout.newTab().setText("xx"));
        mTabLayout.addTab(mTabLayout.newTab().setText("tt"));
//        mTabLayout.addTab(mTabLayout.newTab().setCustomView());

        myAdapter = new MyAdapter(getSupportFragmentManager());
        String[] arrayStrings = getResources().getStringArray(R.array.viewpager);
        myAdapter.setTab(arrayStrings);

        mViewPager = (ViewPager) findViewById(R.id.vf_viewpagerId);
        mViewPager.setAdapter(myAdapter);//1
        mTabLayout.setupWithViewPager(mViewPager);//2

    }
}
