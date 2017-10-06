package com.vsahin.twitter_api_search.Di;

import android.app.Application;

import com.vsahin.twitter_api_search.Model.RetrofitServices.TweetService;
import com.vsahin.twitter_api_search.Model.Repository.TweetRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Volkan Şahin on 18.09.2017.
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application providesApplication() {
        return application;
    }

    @Singleton
    @Provides
    Converter.Factory provideGsonConverter(){
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Converter.Factory converter){
        return new Retrofit.Builder()
                .baseUrl("https://api.twitter.com/")
                .addConverterFactory(converter)
                .build();
    }

    @Singleton
    @Provides
    TweetService provideTweetService(Retrofit retrofit){
        return retrofit.create(TweetService.class);
    }
}
