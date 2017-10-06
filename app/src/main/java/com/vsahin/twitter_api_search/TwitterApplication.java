package com.vsahin.twitter_api_search;

import android.app.Application;

import com.vsahin.twitter_api_search.Di.AppComponent;
import com.vsahin.twitter_api_search.Di.AppModule;
import com.vsahin.twitter_api_search.Di.DaggerAppComponent;

/**
 * Created by Volkan Şahin on 18.09.2017.
 */

public class TwitterApplication extends Application{
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

}
