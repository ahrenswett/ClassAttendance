package com.ahrenswett.classattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class Attendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
    }




//    check if all  checked checked
    public static boolean checker(LinearLayout ll){
        for (int i=0; i<ll.getChildCount(); i++){
            CheckBox cb = (CheckBox)ll.getChildAt(i);
            if(!cb.isChecked()){
                return false;
            }
        }
        return true;
    }
}
