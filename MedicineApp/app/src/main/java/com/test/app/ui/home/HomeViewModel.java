package com.test.app.ui.home;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.test.app.MyApplication;
import com.test.app.R;
import com.test.app.data.model.Cart;
import com.test.app.data.model.Medicine;
import com.test.app.ui.base.BaseViewModel;
import com.test.app.utils.Utils;

import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {

    public ObservableList<Medicine> medicineList = new ObservableArrayList<>();


    void getMedicines() {
        setIsLoading(true);
        getCompositeDisposable()
                .add(getDataManager().getMedicines()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Medicine>>() {
                            @Override
                            public void accept(List<Medicine> medicines) {
                                setIsLoading(false);
                                if (medicines != null) {
                                    medicineList.clear();
                                    medicineList.addAll(medicines);
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable t) {
                                setIsLoading(false);
                                getNavigator().handleError(t);
                                t.printStackTrace();
                            }
                        }));

    }

    void addToCart(Medicine medicine) {
        Cart cart = new Cart(medicine);
        getCompositeDisposable().add(getDataManager().addToCart(cart)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Action() {
                    @Override
                    public void run() {
                        Utils.showToast(MyApplication.getInstance().getApplicationContext()
                                .getString(R.string.added_to_cart));

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Utils.showToast(MyApplication.getInstance().getApplicationContext()
                                .getString(R.string.error_pls_try_again));

                    }
                }));
    }
}
