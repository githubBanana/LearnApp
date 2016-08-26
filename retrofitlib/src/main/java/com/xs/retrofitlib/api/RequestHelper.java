package com.xs.retrofitlib.api;

import android.util.Log;

import com.google.gson.Gson;
import com.xs.retrofitlib.model.GetRankModel;
import com.xs.retrofitlib.model.GetTopModel;
import com.xs.retrofitlib.model.LabelTopicModel;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-04-26 16:04
 * @email Xs.lin@foxmail.com
 */
public class RequestHelper {

    public static final String WEB_SERVICE = "http://wd.zfwsc.com/WebService/WeiYunDong.asmx/";

    private static ApiService mApiService;







    /**
     * GetRank
     * 分页获取排名榜单
     * PageIndex:页码
     * PageSize:页数量
     *
     * @param PageIndex
     * @return
     */
    public static Observable<GetRankModel> requestGetRank(String PageIndex) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("PageIndex", PageIndex);
        map.put("PageSize", 10);
        return request("GetRank", map, GetRankModel.class);
    }

    /**
     * GetLabelTopic
     * 获取标签朋友圈消息
     *
     * @return
     */
    public static Observable<LabelTopicModel> getLabelTopic() {
        return request("GetLabelTopic", LabelTopicModel.class);
    }

    /**
     * GetTop
     * 获取轮播图
     *
     * @return
     */
    public static Observable<GetTopModel> getTop() {
        return request("GetTop", GetTopModel.class);
    }


    /**************************************************************************************************8*/

    static {

        /////////////////////////////////////////

    /*    OkHttpClient client1 = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return null;
            }
        }).build();*/
        ///////////////////////////////////////
        File externalStorageDirectory = android.os.Environment.getExternalStorageDirectory();
        okhttp3.Cache cache = new okhttp3.Cache(new File(externalStorageDirectory,"cache"),1024*1024);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Response response = chain.proceed(chain.request());
            ResponseBody responseBody = response.body();
            String result = responseBody.string();
            Log.e("Client", "intercept#result no decrypt:" + result);
            result = DESUtil.decryptDoNet(result);
            Log.e("Client", "intercept#result:" + result);
            ResponseBody newResponseBody = ResponseBody.create(responseBody.contentType(),result);
            return  response.newBuilder().body(newResponseBody).build();
        }).cache(cache).build();

        //初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(WEB_SERVICE)
                .client(client)
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        mApiService = retrofit.create(ApiService.class);
    }

    private static ApiService getApiService(){
        return mApiService;
    }


    /**
     * 请求网络入口
     *
     * @param method
     * @param map
     * @param cla
     * @param <T>
     * @return
     */
    private static <T> Observable<T> request(String method, Map<String, Object> map, Class<T> cla) {
        Observable<String> observable = getApiService().post2(method, encryptMap(map)).
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread());
        return observable.map(s -> new Gson().fromJson(s, cla));
    }

    /**
     * 请求网络入口,无参请求
     *
     * @param method
     * @param cla
     * @param <T>
     * @return
     */
    private static <T> Observable<T> request(String method, Class<T> cla) {
        Observable<String> observable = getApiService().post3(method).
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread());
        return observable.map(s -> new Gson().fromJson(s, cla));
    }

    /**
     * 请求网络入口,无参请求
     *
     * @param method
     * @return
     */
    private static Observable<String> request(String method) {
        return getApiService().post3(method).
                subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 加密Map
     *
     * @return
     */
    private static Map<String, Object> encryptMap(Map<String, Object> map) {

        String json = new Gson().toJson(map);
        String encryptContent = DESUtil.encryptAsDoNet(json);
        map.clear();
        Log.e("RequestHelper", "encryptMap start:" + json);
        map.put("json", encryptContent);
        Log.e("RequestHelper", "encryptMap end:" + encryptContent);
        return map;
    }

    /**
     * 解密Map
     *
     * @param encryptInfo
     * @return
     */
    public static Map<String, Object> encryptDoMap(String encryptInfo) {
        String decryptInfo = DESUtil.decryptDoNet(encryptInfo);
        return getMapForJson(decryptInfo);
    }

    /**
     * Json 转成 Map<>
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> getMapForJson(String jsonStr) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonStr);
            Iterator<String> keyIter = jsonObject.keys();
            String key;
            String value;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.getString(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

}
