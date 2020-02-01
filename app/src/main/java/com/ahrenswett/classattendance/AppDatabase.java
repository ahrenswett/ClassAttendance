package com.ahrenswett.classattendance;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Class.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClassDao classDao();
}

