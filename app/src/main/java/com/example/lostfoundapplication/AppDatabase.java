package com.example.lostfoundapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {LostFoundItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LostFoundDao lostFoundDao();
}
