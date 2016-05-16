package com.xs.justtest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-05-16 16:01
 * @email Xs.lin@foxmail.com
 */
public class AppAdapter extends BaseAdapter {
    private static final String TAG = "AppAdapter";
    List<ResolveInfo> mList ;
    private Context mContext;
    public AppAdapter(Context mContext) {
        this.mContext = mContext;
        final PackageManager pm = mContext.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        mList = pm.queryIntentActivities(intent,0);
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
            final ViewHolder mViewHolder = new ViewHolder();
            mViewHolder.iv = (ImageView) view.findViewById(R.id.iv_app_Id);
            mViewHolder.tv = (TextView) view.findViewById(R.id.tv_app_Id);
            view.setTag(mViewHolder);
        }
        final ResolveInfo resolveInfo = mList.get(position);
        final PackageManager pm = mContext.getPackageManager();
        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.iv.setImageDrawable(resolveInfo.loadIcon(pm));
        viewHolder.tv.setText(resolveInfo.loadLabel(pm));
        viewHolder.iv.setOnClickListener((view1)->{
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(resolveInfo.activityInfo.packageName,resolveInfo.activityInfo.name));
            mContext.startActivity(intent);
        });
        return view;
    }

    class ViewHolder {
        ImageView iv;
        TextView tv;
    }
}
