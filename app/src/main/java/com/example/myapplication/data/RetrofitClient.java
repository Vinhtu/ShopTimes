package com.example.myapplication.data;

import com.example.myapplication.data.api.AccountApi;
import com.example.myapplication.data.api.BrandApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String BASE_URL = "http://192.168.1.10:3000/api/";

    private static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static AccountApi getAccountService() {
        AccountApi accountApi = getRetrofit().create(AccountApi.class);

        return accountApi;
    }

    public static BrandApi getBrandService() {
        BrandApi brandApi = getRetrofit().create(BrandApi.class);

        return brandApi;
    }
}
