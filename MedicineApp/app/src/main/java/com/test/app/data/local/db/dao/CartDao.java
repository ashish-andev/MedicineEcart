package com.test.app.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.test.app.data.model.Cart;

import java.util.List;

@Dao
public interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertToCart(Cart cart);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCart(Cart cart);

    @Query("SELECT * from Cart WHERE `medicineId` = :id")
    Cart getCart(int id);

    @Query("SELECT * from Cart")
    List<Cart> getCartList();

    @Delete()
    void deleteCart(Cart cart);

    @Query("DELETE FROM Cart")
    void deleteAllCart();

}
