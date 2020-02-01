package com.ahrenswett.classattendance;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity
public class Class {
    @ColumnInfo(name = "class_name")
    private String className;
    private int size;



    @PrimaryKey(autoGenerate = true)
    private long id;


    Class(String className, int size) {
        this.className = className;
        this.size = size;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
