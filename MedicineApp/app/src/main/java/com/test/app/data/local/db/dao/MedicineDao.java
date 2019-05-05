package com.test.app.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.test.app.data.model.Medicine;

import java.util.List;

@Dao
public interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMedicine(List<Medicine> medicines);

    @Query("SELECT * from Medicine ORDER BY brandname")
    List<Medicine> getMedicines();

    @Query("DELETE from Medicine")
    void deleteMedicines();

}
