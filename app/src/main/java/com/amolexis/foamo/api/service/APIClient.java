package com.amolexis.foamo.api.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String BASE_URL = "https://pestiebuggie.xanatest.com";

    public static Retrofit retrofit = null;
    public static Retrofit.Builder retroBuilder;

    public Retrofit getClient(){
        if (retrofit == null){
            retroBuilder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

            return retroBuilder.build();
        }
        return retrofit;
    }
}
