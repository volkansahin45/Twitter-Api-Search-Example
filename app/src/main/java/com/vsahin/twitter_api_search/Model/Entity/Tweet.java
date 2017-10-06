package com.vsahin.twitter_api_search.Model.Entity;

import java.util.ArrayList;

/**
 * Created by Volkan Şahin on 18.09.2017.
 */

public class Tweet {
    private ArrayList<Status> statuses;
    private TweetMetaData search_metadata;

    public ArrayList<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(ArrayList<Status> statuses) {
        this.statuses = statuses;
    }

    public TweetMetaData getSearch_metadata() {
        return search_metadata;
    }

    public void setSearch_metadata(TweetMetaData search_metadata) {
        this.search_metadata = search_metadata;
    }

    public void isSame(Tweet tweet){

    }
}
