package com.xs.viewpagertest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xs.viewpagertest.R;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-04-23 15:50
 * @email Xs.lin@foxmail.com
 */
public class MyFragment extends Fragment {
    private static final String TAG = "MyFragment";
    private int type;
    private static final String TYPE = "type";
    private TextView _textView;
    /**
     * Create a new instance of CountingFragment, providing "num"
     * as an argument.
     */
    public static MyFragment newInstance(int type) {
        MyFragment myFragment = new MyFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt(TYPE,type);
        myFragment.setArguments(args);
        return myFragment;
    }
    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.type = getArguments() != null ? getArguments().getInt(TYPE) : 1;
    }

    /**
     * The Fragment's UI is just a simple text view showing its
     * instance number.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            View rootView = inflater.inflate(R.layout.myfragment_layout, container, false);
            initView(rootView);
            return rootView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initView(View mView) {
        _textView = (TextView) mView.findViewById(R.id.fragment_tv_Id);
        _textView.setText(TYPE+":"+type);
        Log.e(TAG, "----------------------initView----------------------------" + type);

    }


}
