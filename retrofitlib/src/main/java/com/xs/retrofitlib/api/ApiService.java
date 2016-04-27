package com.xs.retrofitlib.api;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-04-26 16:04
 * @email Xs.lin@foxmail.com
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("{method}")
    Call<String> post(@Path("method") String method, @FieldMap Map<String, Object> fieldMap);

    @Headers("Cache-Control:max-age=640000")
    @FormUrlEncoded
    @POST("{method}")
    Observable<String> post2(@Path("method") String method, @FieldMap Map<String, Object> fieldMap);

    @POST("{method}")
    Observable<String> post3(@Path("method") String method);

    @Multipart
    @POST("{method}")
    Observable<String> post4(@Path("method") String method, @PartMap Map<String, RequestBody> params);
}
