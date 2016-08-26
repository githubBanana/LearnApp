package com.xs.retrofitlib.test;

import com.xs.retrofitlib.model.LabelTopic;

import java.util.List;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-06-12 14:55
 * @email Xs.lin@foxmail.com
 */
public class RequestTest {
    private static final String TAG = "RequestTest";
    public static GithubService githubService ;


    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .build();
        githubService = retrofit.create(GithubService.class);
    }

    public static Call<List<LabelTopic>> getRepo() {
        return githubService.listRepos("lin");
    }
}
