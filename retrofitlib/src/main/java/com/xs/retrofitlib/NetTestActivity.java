package com.xs.retrofitlib;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xs.retrofitlib.api.RequestHelper;
import com.xs.retrofitlib.model.GetRankModel;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-04-26 17:47
 * @email Xs.lin@foxmail.com
 */
public class NetTestActivity extends BaseActivity{
    private static final String TAG = "NetTestActivity";

    private TextView _tvNet1,_tvNet2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nettest_layout);
        _tvNet1 = (TextView) findViewById(R.id.tv_net1_Id);
        _tvNet2 = (TextView) findViewById(R.id.tv_net2_Id);
        _tvNet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetRank();
            }
        });

        _tvNet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void GetRank() {

        Observable<GetRankModel> observable = RequestHelper.requestGetRank("1");
        Subscription subscription = observable.subscribe(new Subscriber<GetRankModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GetRankModel getRankModel) {

            }
        });
        addSubscription(subscription);
    }
}
