package com.xs.retrofitlib;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xs.retrofitlib.api.MySubscriber;
import com.xs.retrofitlib.api.RequestHelper;
import com.xs.retrofitlib.model.GetRankModel;
import com.xs.retrofitlib.model.GetTopModel;
import com.xs.retrofitlib.model.LabelTopicModel;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

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
//                GetRank();
            }
        });

        _tvNet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getLableTopicAndTop();
                test("zhongguo","dssd");

            }
        });

    }
    private void test(String... string) {
     /*   Observable.from(string).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG,"onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG,"onNext:"+s);
            }
        });*/

        Observable.just("fsf","rrr","dsdsdsds").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG,"onCompleted");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG,"onNext:"+s);

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

    /*连续访问*/
    private void getLableTopicAndTop() {
        Observable<GetTopModel> getTopModelObservable = RequestHelper.getTop();
        Observable<LabelTopicModel> getLabelTopicModelObservable = RequestHelper.getLabelTopic();
        Subscription subscription = Observable.zip(getTopModelObservable,getLabelTopicModelObservable,(a,b) -> {
            if (a.isSuccessed()) {
                Log.e(TAG,"a.isSuccessed(!!!!!!!!!!!!!!!!!!!!!!!");

            }
            if(b.isSuccessed()) {
                Log.e(TAG,"b.isSuccessed()(!!!!!!!!!!!!!!!!!!!!!!!");
            }
            return null;
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new MySubscriber<Object>() {
            @Override
            public void onNext(Object o) {
                Log.e(TAG,"onNext!!!!!!!!!!!!!!!!!!!!!!!");
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(TAG, "onError!!!!!!!!!!!!!!!!!!!!!!!");

            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                Log.e(TAG, "onCompleted!!!!!!!!!!!!!!!!!!!!!!!");

            }
        });
        addSubscription(subscription);
    }
}
