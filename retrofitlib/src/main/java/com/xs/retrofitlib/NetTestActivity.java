package com.xs.retrofitlib;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xs.retrofitlib.api.DESUtil;
import com.xs.retrofitlib.api.MySubscriber;
import com.xs.retrofitlib.api.RequestHelper;
import com.xs.retrofitlib.model.GetRankModel;
import com.xs.retrofitlib.model.GetTopModel;
import com.xs.retrofitlib.model.LabelTopicModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-04-26 17:47
 * @email Xs.lin@foxmail.com
 */
public class NetTestActivity extends BaseActivity{
    private static final String TAG = "NetTestActivity";

    private TextView _tvNet1,_tvNet2;

    public String message;
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
                message = DESUtil.encryptAsDoNet("nihaoqingwennishi");
                Log.e(TAG, "encryptAsDoNet: "+message);
            }
        });

        _tvNet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getLableTopicAndTop();
//                test5();
//                String bb = DESUtil.decryptDoNet(message);
//                Log.e(TAG, "decryptDoNet: "+bb );

                testOKhttpClient();
            }
        });

    }
    private void test1(String... string) {
        /**
         1*/
        Observable.from(string).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext:" + s);
            }
        });
    }


        /**
         * 2
         */
    private void test2() {
        Observable.just("call").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "" + s);
            }
        });
    }
        /**
         * 3
         */
    private void test3() {
        Observable.just("fsf", "rrr", "dsdsdsds").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext:" + s);

            }
        });
    }

        /**
         *4
         */
        private void test4() {
            Observable<Integer> observable = Observable.just(9, 3, 4);
            observable.subscribe(new Subscriber<Integer>() {
                @Override
                public void onCompleted() {
                    Log.e(TAG,"test4-onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG,"test4-onError");
                }

                @Override
                public void onNext(Integer integer) {
                    Log.e(TAG,"test4-onNext:"+integer);

                }
            });
        }

    /**
     *
     */
    private void test5() {
        Observable observable = Observable.just(2);
        Subscription subscription = observable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                Log.e(TAG,"tes5-onCompleted:");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,"test5-onError:"+e);
            }

            @Override
            public void onNext(Object o) {
                Log.e(TAG,"test5-onNext:"+o);
            }
        });
        if (compositeSubscription == null)
            compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(subscription);
    }
    private CompositeSubscription compositeSubscription = null;



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

    private static OkHttpClient client;
    static {
        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                ResponseBody responseBody = response.body();
                String result = responseBody.string();
                Log.e(TAG, "intercept: "+result );
                StringBuilder str = new StringBuilder();
                str.append("xixixixixixixixix").append(result);
              /*  Log.e(TAG, "intercept: 解密前"+result );
                result = DESUtil.decryptDoNet(result);
                Log.e(TAG, "intercept: 解密后"+result );*/
                ResponseBody newResponseBody = ResponseBody.create(responseBody.contentType(),result);
                return response.newBuilder().body(newResponseBody).build();
            }
        }).build();
    }
    private void testOKhttpClient() {

        Request r = new Request.Builder().url("http://www.baidu.com").build();
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: "+response.body().string() );
            }
        };
         client.newCall(r).enqueue(callback);
        /*Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Request request = new Request.Builder().url("http://www.baidu.com")
                .build();//header("Content-Type","text/plain")
                Log.e(TAG, "call: "+client.protocols().get(0));
//                OkHttpClient client = new OkHttpClient();

                try {
                    Response response = client.newCall(request).execute();
                    String result = response.body().string();
                    Log.e(TAG, "testOKhttpClient execute : "+ result);
                    subscriber.onNext(result);
                    subscriber.onCompleted();
                    Log.e(TAG, "call: done" );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: lalala" );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: "+s );
            }
        });*/




    }

}
