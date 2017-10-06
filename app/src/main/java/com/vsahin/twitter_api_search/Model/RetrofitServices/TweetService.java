package com.vsahin.twitter_api_search.Model.RetrofitServices;

import android.arch.lifecycle.LiveData;

import com.vsahin.twitter_api_search.Model.Entity.AuthenticatedData;
import com.vsahin.twitter_api_search.Model.Entity.Tweet;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Volkan Şahin on 18.09.2017.
 */

public interface TweetService {

    @FormUrlEncoded
    @POST("/oauth2/token")
    Call<AuthenticatedData> authenticate(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grantType
    );

    @GET("1.1/search/tweets.json")
    Call<Tweet> getTweets(
            @Header("Authorization") String authorization,
            @Query("max_id") long id,
            @Query("q") String searchingText
    );
}
