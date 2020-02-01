package com.ahrenswett.classattendance;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class AddClass extends AppCompatActivity {
    static int[] studentArr;
    static Boolean allPresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class);


//        setup scrollview
        final LinearLayout ll = findViewById(R.id.ll);
        final EditText classSize = findViewById(R.id.classSize);
        final EditText className = findViewById(R.id.className);
//        Set the button to pass the class size to the checkbox generator
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.db.classDao().addClass(new Class(className.getText().toString(), Integer.parseInt(classSize.getText().toString())));
                finish();
            }
        });
    }

}