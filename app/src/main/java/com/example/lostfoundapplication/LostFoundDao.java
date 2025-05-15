package com.example.lostfoundapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LostFoundDao {

    @Insert
    void insert(LostFoundItem item);

    @Query("SELECT * FROM lost_found_items")
    List<LostFoundItem> getAllItems();

    @Query("SELECT * FROM lost_found_items WHERE id = :id")
    LostFoundItem getItemById(int id);

    @Delete
    void delete(LostFoundItem item);
}
