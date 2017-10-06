package com.vsahin.twitter_api_search.Di;

import com.vsahin.twitter_api_search.View.TweetList.TweetListViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Volkan Şahin on 18.09.2017.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(TweetListViewModel TweetListViewModel);
}
