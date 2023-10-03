package com.example.datamanagingfrontend.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService{

    private Retrofit retrofit;

    public RetrofitService(){
        initializeRetrofit();
    }

    private void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.77:8080")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
