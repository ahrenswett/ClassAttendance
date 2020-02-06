package com.ahrenswett.classattendance;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Class {
    @ColumnInfo(name = "class_name")
    private String className;
    private int size;
    @TypeConverter
    private List studentsInClass(long value){
        return value == null ? null : new 
    }

    @PrimaryKey(autoGenerate = true)
    private long id;

    Class(String className) {
        this.className = className;
        this.size = 0;

        //TODO: should I implement a list that that holds all
        this.studentsInClass = new ArrayList();


    }

    String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    int getSize() {
        return size;
    }

    void setSize(int size) {
        this.size = size;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List getStudentsInClass() {
        return studentsInClass;
    }

    public void setStudentsInClass(List<Class> studentsInClass) {
        this.studentsInClass = studentsInClass;
    }
}
