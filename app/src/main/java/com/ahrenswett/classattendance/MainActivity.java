package com.ahrenswett.classattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClassAdapter.ClassInteractionListener{
    protected static final String TAG = "ahren.Main";
    private static final String DATABASE_NAME = "classes_db";
    protected static AppDatabase db;
    protected List<Class> classes;
    RecyclerView classRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        renderClassestoRecyclerView();



//        TODO: Set up floating action button onClick intent
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddClass = new Intent(MainActivity.this, AddClass.class);
                MainActivity.this.startActivity(goToAddClass);
            }
        });
    }
    protected void onResume(){
        super.onResume();
        renderClassestoRecyclerView();
    }

//    TODO: Render the list that is being Generated to the View

    private void renderClassestoRecyclerView() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        this.classes = new LinkedList<>();
//        add all classes into the linked list
        this.classes.addAll(db.classDao().getAll());
        Log.wtf(TAG,"SUCCESS classes is: "+classes.size());

        classRecycler = findViewById(R.id.classRecycler);
        classRecycler.setLayoutManager(new LinearLayoutManager(this));
        classRecycler.setAdapter(new ClassAdapter(classes, MainActivity.this));


        }

    @Override
    public void classCommand(Class classSession) {

    }
}
