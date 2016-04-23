package com.xs.learnapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xs.learnapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-04-23 10:46
 * @email Xs.lin@foxmail.com
 */
public class MainListAdapter extends BaseAdapter {
    private static final String TAG = "MainListAdapter";

    private List<String> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    public MainListAdapter(Context context,List<String> mList) {
        this.mList = mList;
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.main_list_item,null);
            mViewHolder.mTv = (TextView) convertView.findViewById(R.id.main_list_item_tvId);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.mTv.setText(mList.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView mTv;
    }
}
