package com.android.study.magicsev2_retrofit.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.study.magicsev2_retrofit.model.RetrofitClient;
import com.dreamsecurity.e2e.MagicSE2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitViewModel extends ViewModel {
    public MutableLiveData<String> liveDataServerCert = new MutableLiveData<>();
    public MutableLiveData<String> liveDataPlainText = new MutableLiveData<>();
    public MutableLiveData<String> liveServerEncData = new MutableLiveData<>();
    public MutableLiveData<String> liveDataDecData = new MutableLiveData<>();
    private String sessionKey = null;
    MagicSE2 magicSE2;
    RetrofitClient client = null;

    public RetrofitViewModel(MagicSE2 magicSE2) {
        this.magicSE2 = magicSE2;
    }

    public void getServerCert() {
        client = new RetrofitClient();
        Call<String> requestServerCert = client.getApiService().getCert();
        requestServerCert.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body() != null) {
                    liveDataServerCert.postValue(response.body().trim());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("fail", "request server cert fail");
            }
        });
    }

    public void sendSessionKeyAndEncData() {
        String encData = makeSessionKey(liveDataPlainText.getValue());
        Call<String> sendSessionKey = client.getApiService().sendSessionKey(encData);
        sendSessionKey.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body() != null){
                    liveServerEncData.setValue(response.body().trim());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("fail", "send SessionKey and EncData fail");
            }
        });
    }

    public void decData() {
        try {
            liveDataDecData.setValue(new String(magicSE2.MagicSE_DecData(
                    sessionKey,
                    liveServerEncData.getValue())
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String makeSessionKey(String plainText) {
        String encData = null;
        try {
            sessionKey = magicSE2.MagicSE_MakeSessionKey(liveDataServerCert.getValue(), null);
            encData = magicSE2.MagicSE_EncData(sessionKey, plainText.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encData;
    }
}
