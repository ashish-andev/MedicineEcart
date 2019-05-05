package com.test.app.data.local.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.test.app.data.model.Cart;
import com.test.app.data.model.Medicine;
import com.test.app.data.model.Order;

import java.util.List;

public class AppDbHelper {

    private final AppDatabase mAppDatabase;
    private static AppDbHelper appDbHelper;


    private AppDbHelper(Context context) {
        this.mAppDatabase = Room.databaseBuilder(context,
                AppDatabase.class, "med_ecom")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static AppDbHelper getInstance(Context context) {
        if (appDbHelper == null) {
            appDbHelper = new AppDbHelper(context);
        }
        return appDbHelper;
    }


    public void addMedicines(List<Medicine> medicines) {
        mAppDatabase.medicineDao().insertMedicine(medicines);
    }

    public List<Medicine> getAllMedicines() {
        return mAppDatabase.medicineDao().getMedicines();
    }


    public void addToCart(Cart cart) {
        Cart existingCart = mAppDatabase.cartDao().getCart(cart.medicineId);
        if (existingCart != null) {
            existingCart.quantity += 1;
            mAppDatabase.cartDao().updateCart(existingCart);
        } else {
            cart.quantity = 1;
            mAppDatabase.cartDao().insertToCart(cart);
        }

    }

    public void updateCart(Cart cart) {
        mAppDatabase.cartDao().updateCart(cart);

    }

    public void removeFromCart(Cart cart) {
        mAppDatabase.cartDao().deleteCart(cart);
    }

    public void removeAllFromCart() {
        mAppDatabase.cartDao().deleteAllCart();
    }

    public List<Cart> getCartList() {
        return mAppDatabase.cartDao().getCartList();
    }

    public void addToOrder(List<Order> orders) {
        mAppDatabase.orderDao().insertToOrder(orders);

    }
}
