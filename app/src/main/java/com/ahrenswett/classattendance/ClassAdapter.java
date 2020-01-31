package com.ahrenswett.classattendance;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ahrenswett.classattendance.AddClass;
import com.ahrenswett.classattendance.Class;
import com.ahrenswett.classattendance.R;

import java.util.List;

//Adapter needs to extend the RecyclerView.Adapter
//This is done through implementing a ViewHolder that extends the RecyclerView.ViewHolder

class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder>{
//    global vars
    public List<Class> classes;
    ClassInteractionListener listener;


    public interface ClassInteractionListener{
        void classCommand(Class classSession);
    }


    public ClassAdapter(List<Class> classes, ClassInteractionListener listener){
        this.classes = classes;
        this.listener = listener;
    }

//    Declare a ViewHolder that declares a listener and the text areas needed and the object
    class ClassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Class myClass;
        TextView ClassTitleView;
        TextView ClassSizeView;
        ClassInteractionListener listener;

    ClassViewHolder(@NonNull View itemView,ClassInteractionListener listener) {
        super(itemView);
        this.ClassTitleView = itemView.findViewById(R.id.classNameText) ;
        this.ClassSizeView = itemView.findViewById(R.id.classSizeText);
        this.listener = listener;
    }


    @Override
        public void onClick(View view) {
         listener.classCommand(this.myClass);
        }
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_class, parent,false);
        return new ClassViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        Class classAtPosition = this.classes.get(position);
        holder.ClassTitleView.setText(classAtPosition.getClassName());
        holder.ClassSizeView.setText(classAtPosition.getSize());
    }

    @Override
    public int getItemCount() {
        return 0;
    }




}



