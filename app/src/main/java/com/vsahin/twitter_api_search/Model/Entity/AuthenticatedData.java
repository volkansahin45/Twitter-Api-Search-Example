package com.vsahin.twitter_api_search.Model.Entity;

/**
 * Created by Volkan Şahin on 18.09.2017.
 */

public class AuthenticatedData
{
    private String token_type;

    private String access_token;

    public String getToken_type ()
    {
        return token_type;
    }

    public String getAccess_token ()
    {
        return access_token;
    }

}