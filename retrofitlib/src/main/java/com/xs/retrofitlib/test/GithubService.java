package com.xs.retrofitlib.test;

import com.xs.retrofitlib.model.LabelTopic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-06-12 14:42
 * @email Xs.lin@foxmail.com
 */
public interface GithubService {

    @GET("users/{user}/repos")
    Call<List<LabelTopic>> listRepos(@Path("user") String user);

}
