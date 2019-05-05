package com.test.app.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.test.app.data.model.Order;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertToOrder(List<Order> cart);

}
