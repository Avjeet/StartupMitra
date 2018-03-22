package com.teamshunya.silencio.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by himanshusingh on 01/04/17.
 */

public class MyDetail {

    public static final String  BASE_URL="http://138.197.105.186";
    public static Retrofit retrofit=null;
    public static Retrofit getClient() {
        if (retrofit == null) {

            retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static APIInterface getApiService() {
        return getClient().create(APIInterface.class);
    }
}