package com.example.divyanshsingh.transportationmanagement.API;

import android.content.Context;


import android.content.Context;


import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public static Retrofit retrofit = null;

    /**
     * @return
     */
    public static ApiInterface getApiInterfaceInt(Context context) {

        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        Cache cache;
        cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://intermediasutra-env.w84r34bwj9.ap-south-1.elasticbeanstalk.com/webapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient().newBuilder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .cache(cache)
                        .build())
                .build();

        return retrofit.create(ApiInterface.class);
    }


    /**
     * @return retrofit
     */
    protected static Retrofit getRetrofitBuilder() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://intermediasutra-env.w84r34bwj9.ap-south-1.elasticbeanstalk.com/webapi/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    //test at localServer
    public static ApiInterface getApiInterfaceLocal(Context context) {

        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        Cache cache;
        cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.121:8080/MalikAPI/webapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient().newBuilder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .cache(cache)
                        .build())
                .build();

        return retrofit.create(ApiInterface.class);
    }

}
