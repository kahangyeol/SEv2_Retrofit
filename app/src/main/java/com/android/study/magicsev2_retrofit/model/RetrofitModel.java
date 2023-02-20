package com.android.study.magicsev2_retrofit.model;

import com.android.study.magicsev2_retrofit.viewModel.Service;

import retrofit2.Call;

public class RetrofitModel {
    RetrofitClient client = new RetrofitClient();
    public Call<String> getServerCert() {
        return client.getApiService().getCert();
    }

    public Call<String> sendSessionKeyAndEncData(String sessionKey) {
        return client.getApiService().sendSessionKey(sessionKey);
    }
}
