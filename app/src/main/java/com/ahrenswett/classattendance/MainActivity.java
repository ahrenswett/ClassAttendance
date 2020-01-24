package com.ahrenswett.classattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static int[] studentArr;
    boolean allPresent = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        setup scrollview
        final ScrollView scrollView = findViewById(R.id.scrollView);
        final EditText classSize = findViewById(R.id.classSize);


//        Set the button to pass the class size to the checkbox generator
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = Integer.parseInt(classSize.getText().toString());
                studentArr = new int[size];
                final ListView allStudents = new ListView(scrollView.getContext());
                allStudents.setTop(scrollView.getTop());
                scrollView.addView(allStudents);

//        create a new checkbox for each student in the class
                for (int i = 0; i < size; i++) {
                    CheckBox students = new CheckBox(MainActivity.this);
                    students.setText("student " + i);
                    students.setId(i);
                    allStudents.addView(students);
                }
            }
        });
    }

    private static boolean allCheckedIn(){

        return false;
    }
}