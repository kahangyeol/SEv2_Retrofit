package com.android.study.magicsev2_retrofit.viewModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Service {
    @POST("MagicSEv2_GetSvrCert.jsp")
    Call<String> getCert();

    @POST("MagicSEv2_Result.jsp")
    Call<String> sendSessionKey(@Body String sessionKey);
}
