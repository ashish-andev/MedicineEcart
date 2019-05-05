package com.test.app.data.local.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.test.app.data.local.db.dao.CartDao;
import com.test.app.data.local.db.dao.MedicineDao;
import com.test.app.data.local.db.dao.OrderDao;
import com.test.app.data.model.Cart;
import com.test.app.data.model.Medicine;
import com.test.app.data.model.Order;


@Database(entities = {Medicine.class, Cart.class, Order.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MedicineDao medicineDao();

    public abstract CartDao cartDao();

    public abstract OrderDao orderDao();
}