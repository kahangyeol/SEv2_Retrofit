package com.android.study.magicsev2_retrofit.view;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

public class BaseActivity<BV extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {
    BV binding;
    VM viewModel;

    public void bindView(@LayoutRes int layoutId) {
        binding = DataBindingUtil.setContentView(this, layoutId);
    }

    public void bindViewModel(VM viewModel) {
        this.viewModel = viewModel;
    }
}
