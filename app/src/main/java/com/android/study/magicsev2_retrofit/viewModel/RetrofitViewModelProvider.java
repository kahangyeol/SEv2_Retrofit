package com.android.study.magicsev2_retrofit.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dreamsecurity.e2e.MagicSE2;

public class RetrofitViewModelProvider implements ViewModelProvider.Factory {

    MagicSE2 magicSE2;
    public RetrofitViewModelProvider(MagicSE2 magicSE2) {
        this.magicSE2 = magicSE2;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(RetrofitViewModel.class)) {
            return (T) new RetrofitViewModel(magicSE2);
        } else {
            throw new RuntimeException("실패");
        }
    }
}
