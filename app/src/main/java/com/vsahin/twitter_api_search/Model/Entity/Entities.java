package com.vsahin.twitter_api_search.Model.Entity;

import java.util.List;

/**
 * Created by Volkan Şahin on 19.09.2017.
 */

public class Entities {
    private List<Media> media;

    public Media getMedia() {
        if(media != null) {
            return media.get(0);
        } else {
            return null;
        }
    }
}
