package com.vsahin.twitter_api_search.Model.Repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;

import com.vsahin.twitter_api_search.Model.Entity.AuthenticatedData;
import com.vsahin.twitter_api_search.Model.Entity.Tweet;
import com.vsahin.twitter_api_search.Model.RetrofitServices.TweetService;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Volkan Şahin on 18.09.2017.
 */

@Singleton
public class TweetRepository {

    private static final String TAG = "TweetRepository";
    private String API_KEY = "";
    private String API_SECRET = "";
    private String BEARER_KEY = API_KEY + ":" + API_SECRET;

    private String BASE64_BEARER_KEY = "Basic " + Base64.encodeToString(BEARER_KEY.getBytes(), Base64.NO_WRAP);
    private String BODY_GRANT_TYPE = "client_credentials";

    private AuthenticatedData authenticatedData;
    final MutableLiveData<Tweet> tweet = new MutableLiveData<>();

    private TweetService tweetService;
    @Inject
    public TweetRepository(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    public void authenticate(){
        tweetService.authenticate(BASE64_BEARER_KEY, BODY_GRANT_TYPE).enqueue(new Callback<AuthenticatedData>() {
            @Override
            public void onResponse(@NonNull Call<AuthenticatedData> call, @NonNull Response<AuthenticatedData> response) {
                authenticatedData = response.body();
            }

            @Override
            public void onFailure(@NonNull Call<AuthenticatedData> call, @NonNull Throwable t) {}
        });
    }

    public void getTweets(String searchingText, long max_id, Callback<Tweet> callback){
        if(authenticatedData != null){
            String accessToken = "bearer " + authenticatedData.getAccess_token();

            tweetService.getTweets(accessToken, max_id, searchingText).enqueue(callback);
        }
    }
}
