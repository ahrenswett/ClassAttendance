package com.ahrenswett.classattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClassAdapter.ClassInteractionListener{
    protected static final String TAG = "ahren.Main";
    private static final String DATABASE_NAME = "classes_db";
    protected static AppDatabase db;
    protected List<Class> classes;
    RecyclerView classRecycler;
    ClassAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        renderClassesToRecyclerView();
        final LinearLayout[] linearLayout1 = new LinearLayout[1];



        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                consider alert Dialog or dialog frag instead of Popup
                linearLayout1[0] = new LinearLayout(view.getContext());
                LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
                View customView = layoutInflater.inflate(R.layout.pop_up,null);

                final Button submit = customView.findViewById(R.id.submit);
                final EditText className = customView.findViewById(R.id.className);


                //instantiate popup window
                final PopupWindow addClass = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                addClass.setFocusable(true);
                //display the popup window
                addClass.showAtLocation(linearLayout1[0], Gravity.CENTER, 0, 0);


                //Add class to the database
//                TODO: Figure out why null???????
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    MainActivity.db.classDao().addClass(new Class(className.getText().toString()));
                    checkForNewClass();
                    addClass.dismiss();
                    }
                });
//                Intent goToAddClass = new Intent(MainActivity.this, AddClass.class);
//                MainActivity.this.startActivity(goToAddClass);
            }
        });
    }


    protected void onResume()throws NullPointerException{
        super.onResume();
//        See if there was a new class added
        checkForNewClass();
    }


    void checkForNewClass(){
        if(adapter.getItemCount() < db.classDao().getAllClasses().size()) {
//            if there was notify the adapter and add it to the recycler view
            int newClassID = db.classDao().getAllClasses().size(); // store the n
            this.classes.add(db.classDao().getClassById(newClassID));
            adapter.notifyItemInserted(adapter.getItemCount());
//            TODO: Figure out why new classes are shifted to the left :(
        }
    }


    private void renderClassesToRecyclerView() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        this.classes = new LinkedList<>();
//        add all classes into the linked list
        this.classes.addAll(db.classDao().getAllClasses());
        Log.i(TAG,"SUCCESS classes is: "+classes.size());

        classRecycler = findViewById(R.id.classRecycler);
        classRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ClassAdapter(classes, MainActivity.this);
        classRecycler.setAdapter(adapter);
        }



    @Override
    public void classCommand(Class classSession) {
        Intent goToClassPage = new Intent(MainActivity.this,ClassDetailsPage.class);
        goToClassPage.putExtra("ClassID",classSession.getId());
        goToClassPage.putExtra("ClassName",classSession.getClassName());
    }
}
