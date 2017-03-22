package com.teamshunya.silencio.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by himanshusingh on 19/03/17.
 */

public class ApiClientTemp {

    public static final String BASE_URL = "https://api.darksky.net/forecast/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static APIInterface getApiServicee() {
        return getClient().create(APIInterface.class);
    }
}

