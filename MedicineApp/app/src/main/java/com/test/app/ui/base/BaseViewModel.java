package com.test.app.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.test.app.data.AppDataManager;
import com.test.app.utils.rx.SchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseViewModel<N> extends ViewModel {

    private final AppDataManager mDataManager;

    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private final ObservableBoolean mIsEmpty = new ObservableBoolean(false);

    private final SchedulerProvider mSchedulerProvider;

    private CompositeDisposable mCompositeDisposable;

    private WeakReference<N> mNavigator;

    public BaseViewModel() {
        this.mDataManager = AppDataManager.getInstance();
        this.mSchedulerProvider = new SchedulerProvider();
        this.mCompositeDisposable = new CompositeDisposable();
    }


    public void destroy() {
        mCompositeDisposable.dispose();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public AppDataManager getDataManager() {
        return mDataManager;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public ObservableBoolean getIsEmpty() {
        return mIsEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        mIsEmpty.set(isEmpty);
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }
}
