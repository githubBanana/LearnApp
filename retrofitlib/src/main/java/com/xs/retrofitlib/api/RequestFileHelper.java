package com.xs.retrofitlib.api;

import android.util.Log;

import com.google.gson.Gson;
import com.xs.retrofitlib.model.FileModel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @version V1.0 <文件上传>
 * @author: Xs
 * @date: 2016-04-08 15:31
 * @email Xs.lin@foxmail.com
 */
public class RequestFileHelper {

    public static final String WEB_SERVICE = "http://wd.zfwsc.com/Admin/File/";

    private static ApiService mApiService;

    static {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Response response = chain.proceed(chain.request());
            ResponseBody responseBody = response.body();
            String result = responseBody.string();
            Log.e("Client", "intercept#result no decrypt:" + result);
           /* result = DESUtil.decryptDoNet(result);*/
            Log.e("Client", "intercept#result:" + result);
            ResponseBody newResponseBody = ResponseBody.create(responseBody.contentType(), result);
            Response newResponse = response.newBuilder().body(newResponseBody).build();
            return newResponse;
        }).build();

//        初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(WEB_SERVICE)
                .client(client)
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        mApiService = retrofit.create(ApiService.class);
    }

    private static ApiService getApiService() {
        return mApiService;
    }


    /**
     * 图片上传
     * @param imgFile
     * @return
     */
    public static Observable<FileModel> upload(File imgFile) {
        Map<String,RequestBody> map = new HashMap<>();
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpg"), imgFile);
        map.put("image\"; filename=\""+imgFile.getName()+"", fileBody);
        return request("Upload", map, FileModel.class);
    }

    private static <T> Observable<T> request(String method,Map<String, RequestBody> map, Class<T> cla) {
        Observable<String> observable = getApiService().post4(method, map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        return observable.map(s -> new Gson().fromJson(s, cla));
    }


}
