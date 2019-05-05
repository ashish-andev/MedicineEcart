package com.test.app.ui.medine_detail;

import com.test.app.MyApplication;
import com.test.app.R;
import com.test.app.data.model.Cart;
import com.test.app.data.model.Medicine;
import com.test.app.ui.base.BaseViewModel;
import com.test.app.utils.Utils;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MedicineDetailViewModel extends BaseViewModel<MedicineDetailNavigator> {

    public Medicine medicine;


    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public void addToCart() {
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
