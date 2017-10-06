package com.vsahin.twitter_api_search.View.TweetList;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vsahin.twitter_api_search.Model.Entity.Tweet;
import com.vsahin.twitter_api_search.Model.Repository.TweetRepository;
import com.vsahin.twitter_api_search.TwitterApplication;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Volkan Şahin on 18.09.2017.
 */

public class TweetListViewModel extends AndroidViewModel {

    private static final String TAG = "TweetListViewModel";
    @Inject
    TweetRepository tweetRepository;

    MutableLiveData<Tweet> tweet = new MutableLiveData<Tweet>() {};

    public TweetListViewModel(Application application) {
        super(application);

        ((TwitterApplication)getApplication()).getAppComponent().inject(this);
    }

    void authenticate(){
        tweetRepository.authenticate();
    }

    void getTweets(String searchingText, long max_id) {
        Callback<Tweet> callback = new Callback<Tweet>() {
            @Override
            public void onResponse(@NonNull Call<Tweet> call, @NonNull Response<Tweet> response) {
                if (response.isSuccessful()) {
                    tweet.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Tweet> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        };

        tweetRepository.getTweets(searchingText, max_id, callback);
    }
}
