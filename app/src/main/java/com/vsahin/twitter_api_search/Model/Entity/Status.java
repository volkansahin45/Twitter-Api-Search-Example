package com.vsahin.twitter_api_search.Model.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Volkan Şahin on 19.09.2017.
 */

public class Status implements Serializable {
    private Entities entities;
    private TweetUser user;
    private long id;
    private String text;

    public Entities getEntities() {
        return entities;
    }

    public TweetUser getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public long getId() {
        return id;
    }
}
