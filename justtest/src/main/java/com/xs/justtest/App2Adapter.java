package com.xs.justtest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-05-16 17:03
 * @email Xs.lin@foxmail.com
 */
public class App2Adapter extends BaseAdapter {
    private static final String TAG = "App2Adapter";

    private List<ResolveInfo> mList ;
    private Context mContext;
    public App2Adapter(Context mContext) {
        this.mContext = mContext;
        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory("com.xs.justtest.LAUNCHER");
        mList = mContext.getPackageManager().queryIntentActivities(intent,0);
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
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_app_layout,null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.iv = (ImageView) view.findViewById(R.id.iv_app_Id);
            viewHolder.tv = (TextView) view.findViewById(R.id.tv_app_Id);
            view.setTag(viewHolder);
        }

        final ResolveInfo resolveInfo = mList.get(position);
        final PackageManager pm = mContext.getPackageManager();
        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.iv.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
        viewHolder.tv.setText(resolveInfo.loadLabel(pm));
        final View finalView = view;
        viewHolder.iv.setOnClickListener((view1) -> {
            Snackbar.make(finalView,resolveInfo.loadLabel(pm),Snackbar.LENGTH_LONG).show();
        });
        return view;
    }

    class ViewHolder {
        ImageView iv;
        TextView tv;
    }

}
