package com.android.study.magicsev2_retrofit.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.android.study.magicsev2_retrofit.R;
import com.android.study.magicsev2_retrofit.databinding.ActivityMainBinding;
import com.android.study.magicsev2_retrofit.model.RetrofitClient;
import com.android.study.magicsev2_retrofit.viewModel.RetrofitViewModel;
import com.android.study.magicsev2_retrofit.viewModel.RetrofitViewModelProvider;
import com.dreamsecurity.e2e.MagicSE2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity<ActivityMainBinding, RetrofitViewModel> {
    MagicSE2 magicSE2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView(R.layout.activity_main);
        try {
            magicSE2 = new MagicSE2(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bindViewModel(new ViewModelProvider(this, new RetrofitViewModelProvider(magicSE2)).get(RetrofitViewModel.class));
        initGetServerCert();
        initSendSessionKey();
        initDecData();

        binding.setMainViewModel(viewModel);    //main.xml에 바인딩 한 mainViewModel 변수에 viewModel 을 set
    }

    private void initGetServerCert() {
        viewModel.liveDataServerCert.observe(this, serverCert -> {
            binding.tvServerCert.tvContent.setText(serverCert);
        });
    }

    private void initSendSessionKey() {
        viewModel.liveServerEncData.observe(this, encData -> {
            binding.tvServerReceiveData.tvContent.setText(encData);
            binding.etPlainText.clearFocus();
        });
    }

    private void initDecData() {
        viewModel.liveDataDecData.observe(this, decData ->{
            binding.tvDecData.tvContent.setText(decData);
        });
    }

}