package com.test.app.ui.cart;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.test.app.data.model.Cart;
import com.test.app.ui.base.BaseViewModel;

import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class CartViewModel extends BaseViewModel<CartNavigator> {

    public ObservableList<Cart> cartList = new ObservableArrayList<>();

    void getCartItems() {
        setIsLoading(true);
        getCompositeDisposable()
                .add(getDataManager().getCartItems()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Cart>>() {
                            @Override
                            public void accept(List<Cart> cartItems) {
                                setIsLoading(false);
                                if (cartItems != null) {
                                    cartList.clear();
                                    cartList.addAll(cartItems);
                                }

                                if (cartList.isEmpty()) {
                                    setIsEmpty(true);
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

    void updateQuantity(Cart cart) {
        getCompositeDisposable().add(getDataManager()
                .updateCart(cart)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }

    void removeFromCart(Cart cart) {
        getCompositeDisposable().add(getDataManager()
                .removeFromCart(cart)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }

    public void checkout() {
        getCompositeDisposable().add(getDataManager()
                .doCheckOut()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        getNavigator().goToHomeScreen();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }
}
