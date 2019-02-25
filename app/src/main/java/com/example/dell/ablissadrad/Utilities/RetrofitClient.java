package com.example.dell.ablissadrad.Utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String baseurl = "http://192.168.20.11:8000/api/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;



    private RetrofitClient(){

        retrofit = new Retrofit.Builder().baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

     public static  synchronized RetrofitClient getmInstance(){
        if (mInstance == null){
            mInstance = new RetrofitClient();
        }

        return mInstance;
    }

    public API getApi(){
        return retrofit.create(API.class);

    }

}