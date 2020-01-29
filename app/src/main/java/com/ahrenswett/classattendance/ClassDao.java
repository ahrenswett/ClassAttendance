package com.ahrenswett.classattendance;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClassDao {
    @Query("SELECT * FROM class")
    List<Class> getAll();

    @Query("SELECT * FROM class WHERE class_name =:className")
    List<Class> getClassByName(String className);

    @Insert
    void addClass(Class className);

    @Update
    void updateClass(Class className);

    @Delete
    void deleteClass(Class className);
}

