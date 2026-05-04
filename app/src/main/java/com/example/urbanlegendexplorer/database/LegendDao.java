package com.example.urbanlegendexplorer.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.urbanlegendexplorer.model.Legend;

import java.util.List;

@Dao
public interface LegendDao {

    @Insert
    void insert(Legend legend);

    @Update
    void update(Legend legend);

    @Delete
    void delete(Legend legend);

    @Query("SELECT * FROM legends ORDER BY createdAt DESC")
    LiveData<List<Legend>> getAllLegends();

    @Query("SELECT * FROM legends WHERE id = :legendId LIMIT 1")
    LiveData<Legend> getLegendById(String legendId);

    @Query("DELETE FROM legends")
    void deleteAll();
}