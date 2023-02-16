package com.android.study.magicsev2_retrofit.model;

import com.android.study.magicsev2_retrofit.viewModel.Service;

import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;

    public RetrofitClient() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private final String BASE_URL = "http://10.10.28.224:8080/MagicSEv2_Server_renewal/";

    //    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    public Service getApiService() {
        return getInstance().create(Service.class);
    }

    private Retrofit getInstance() {
        return retrofit;
    }
}
