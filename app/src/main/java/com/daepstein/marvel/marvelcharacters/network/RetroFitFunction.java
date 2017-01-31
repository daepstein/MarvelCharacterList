package com.daepstein.marvel.marvelcharacters.network;

import com.daepstein.marvel.marvelcharacters.Interface.RetroInterface;
import com.daepstein.marvel.marvelcharacters.util.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daepstein on 1/31/2017.
 */

public class RetroFitFunction {

    public static RetroInterface getRetroInterface ()
    {
        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.API_URL)
                .client(httpClient.build())
                .build();

        return retrofit.create(RetroInterface.class);

    }

}
