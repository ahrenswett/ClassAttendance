package com.ahrenswett.classattendance;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    List<Student> getAll();

    @Query("SELECT * FROM student WHERE student_name=:studentName")
    List<Class> getStudentByName(String studentName);

    @Query("SELECT * FROM student WHERE studentId=:studentId")
    Class getStudentById(int studentId);

    @Insert
    void addStudent(Student studentName);

    @Update
    void updateStudent(Student studentName);

    @Delete
    void deleteStudent(Student studentName);
}
