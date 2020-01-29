package com.ahrenswett.classattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static int[] studentArr;
    static Boolean allPresent = false;
    protected AppDatabase db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = MainActivity.this.getWindow().getDecorView();

//        setup scrollview
        final ScrollView sv = findViewById(R.id.allStudentsList);
        final LinearLayout ll = findViewById(R.id.ll);
        final EditText classSize = findViewById(R.id.classSize);
        final EditText className = findViewById(R.id.className);
//        Set the button to pass the class size to the checkbox generator
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.classDao().addClass(new Class(className.getText().toString(), Integer.parseInt(classSize.getText().toString())));
//              get size of checkbox list and inittialize an array  in concordance
                int size = Integer.parseInt(classSize.getText().toString());
                studentArr = new int[size];
//        create a new checkbox for each student in the class
                for (int i = 0; i < size; i++) {
                    studentArr[i]=i;
                }
                for(final int student:studentArr){
                    final CheckBox studentBox = new CheckBox(MainActivity.this);
                    studentBox.setText("Student #"+ student);
                    studentBox.setId(student);
                    System.out.println(student);
                    studentBox.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
//                            check if it was checked if it was check if all checked.
                            if(studentBox.isChecked()){
                                allPresent = checker(ll);
                                Toast.makeText(getApplicationContext(),"status" + allPresent, Toast.LENGTH_SHORT).show();
                            }else{
                                allPresent=false;
                                Toast.makeText(getApplicationContext(),"status" + allPresent, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    ll.addView(studentBox);
                }
            }
        });
    }
//    check if all  checked checked
    public static boolean checker(LinearLayout ll){
//        int children = ll.getChildCount();
        for (int i=0; i<ll.getChildCount(); i++){
            CheckBox cb = (CheckBox)ll.getChildAt(i);
            if(!cb.isChecked()){
               return false;
            }
        }
        return true;
    }

}