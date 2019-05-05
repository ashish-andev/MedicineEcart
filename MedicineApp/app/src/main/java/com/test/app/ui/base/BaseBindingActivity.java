package com.test.app.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.test.app.utils.NetworkUtils;


public abstract class BaseBindingActivity<T extends ViewDataBinding, V extends BaseViewModel>
        extends AppCompatActivity {

    private T mViewDataBinding;
    private V mViewModel;

    public abstract int getBindingVariable();

    public abstract
    @LayoutRes
    int getLayoutId();

    public abstract V getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    @Override
    protected void onDestroy() {
        if (getViewModel() != null) {
            getViewModel().destroy();
        }
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void showBackButton() {
        try {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        } catch (Throwable ignored) {
        }
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected();
    }
}

