package com.xs.viewpagertest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xs.viewpagertest.fragment.MyFragment;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-04-23 15:47
 * @email Xs.lin@foxmail.com
 */
public class MyAdapter extends FragmentPagerAdapter {
    private static final String TAG = "MyAdapter";

    private String[] _Titles;

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MyFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        if (_Titles != null)
            return _Titles.length;
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return _Titles[position];
    }

    public void setTab(String[] arrayString) {
        _Titles  = arrayString;
    }
}
