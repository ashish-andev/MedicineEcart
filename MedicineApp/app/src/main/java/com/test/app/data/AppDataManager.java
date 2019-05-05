package com.test.app.data;

import com.google.gson.Gson;
import com.test.app.MyApplication;
import com.test.app.data.local.db.AppDbHelper;
import com.test.app.data.model.Cart;
import com.test.app.data.model.Medicine;
import com.test.app.data.model.MedicineResponse;
import com.test.app.data.model.Order;
import com.test.app.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Action;

public class AppDataManager {

    private final AppDbHelper appDbHelper;
    private static AppDataManager appDataManager;

    private AppDataManager() {
        appDbHelper = AppDbHelper.getInstance(MyApplication.getInstance().getApplicationContext());
    }

    public static AppDataManager getInstance() {
        if (appDataManager == null) {
            appDataManager = new AppDataManager();
        }
        return appDataManager;
    }

    public Single<List<Medicine>> getMedicines() {
        return Single.create(new SingleOnSubscribe<List<Medicine>>() {
            @Override
            public void subscribe(SingleEmitter<List<Medicine>> emitter) {
                List<Medicine> medicines = appDbHelper.getAllMedicines();

                if (medicines == null || medicines.isEmpty()) {
                    MedicineResponse response = new Gson()
                            .fromJson(Utils.loadJSONFromAsset("medicines.json"), MedicineResponse.class);

                    if (response != null && response.medicines != null
                            && response.medicines.size() > 0) {
                        medicines = new ArrayList<>(response.medicines);
                        appDbHelper.addMedicines(medicines);
                    }
                }
                if (medicines != null) {
                    emitter.onSuccess(medicines);
                } else {
                    emitter.onError(new Exception("Exception in Parsing or in Database  operation"));
                }
            }
        });
    }

    public Completable addToCart(final Cart cart) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() {
                appDbHelper.addToCart(cart);

            }
        });
    }

    public Single<List<Cart>> getCartItems() {
        return Single.create(new SingleOnSubscribe<List<Cart>>() {
            @Override
            public void subscribe(SingleEmitter<List<Cart>> emitter) {
                emitter.onSuccess(appDbHelper.getCartList());
            }
        });
    }

    public Completable updateCart(final Cart cart) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() {
                appDbHelper.updateCart(cart);

            }
        });
    }

    public Completable removeFromCart(final Cart cart) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() {
                appDbHelper.removeFromCart(cart);

            }
        });
    }

    public Completable doCheckOut() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() {
                List<Cart> cartList = appDbHelper.getCartList();
                if (!cartList.isEmpty()) {
                    List<Order> orderList = new ArrayList<>();

                    for (Cart cart : cartList) {
                        Order order = new Order();
                        order.medicineId = cart.medicineId;
                        order.drugcode = cart.drugcode;
                        order.brandname = cart.brandname;
                        order.genericname = cart.genericname;
                        order.packsize = cart.packsize;
                        order.price = cart.price;
                        order.company = cart.company;
                        order.drugnature = cart.drugnature;
                        order.drugtype = cart.drugtype;
                        order.quantityavailable = cart.quantityavailable;
                        order.available = cart.available;
                        order.createdate = cart.createdate;
                        order.updatedate = cart.updatedate;
                        order.schedule = cart.schedule;
                        order.discount = cart.discount;
                        order.maxquantity = cart.maxquantity;
                        order.genericdosage = cart.genericdosage;
                        order.dosage = cart.dosage;
                        order.sellingprice = cart.sellingprice;
                        order.cimscategory = cart.cimscategory;
                        order.cimssubcategory = cart.cimssubcategory;
                        order.clasification = cart.clasification;
                        order.category = cart.category;
                        orderList.add(order);
                    }

                    appDbHelper.addToOrder(orderList);
                    appDbHelper.removeAllFromCart();
                }

            }
        });
    }
}
