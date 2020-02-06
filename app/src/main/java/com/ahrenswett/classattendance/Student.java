package com.ahrenswett.classattendance;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Student {
    @ColumnInfo(name = "student_name")
    private String studentName;
    private String sex;
    private String age;
    private List<Class> classesEnrolledIn;

    @PrimaryKey(autoGenerate = true)
    private long studentId;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<Class> getClassesEnrolledIn() {
        return classesEnrolledIn;
    }

    public void setClassesEnrolledIn(List<Class> classesEnrolledIn) {
        this.classesEnrolledIn = classesEnrolledIn;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}

