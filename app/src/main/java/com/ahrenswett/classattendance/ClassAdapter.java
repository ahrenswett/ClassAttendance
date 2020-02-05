package com.ahrenswett.classattendance;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

//Adapter needs to extend the RecyclerView.Adapter
//This is done through implementing a ViewHolder that extends the RecyclerView.ViewHolder

class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder>{
    //    global vars
    private List<Class> classes;
    private ClassInteractionListener listener;


    public interface ClassInteractionListener{
        void classCommand(Class classSession);
    }


    private static void onBind(ClassViewHolder holder, Class classAtPosition){
        holder.myClass = classAtPosition;
        holder.ClassTitleView.setText(classAtPosition.getClassName());
        holder.ClassSizeView.setText(String.valueOf(classAtPosition.getSize()));
    }


    ClassAdapter(List<Class> classes, ClassInteractionListener listener){
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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.classCommand(this.myClass);
        }

    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(MainActivity.TAG, "IN onCreateViewHolder");
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_class, parent,false);
        return new ClassViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        Log.i(MainActivity.TAG, "IN onBindViewHolder");
        Class classAtPosition = this.classes.get(position);
        ClassAdapter.onBind(holder, classAtPosition);

    }

    @Override
    public int getItemCount() {
        return this.classes.size();
    }

    void add (Class classAtPosition, int position){
        classes.add(classAtPosition);
        notifyItemInserted(position);
    }

    void remove (int position){
        classes.remove(position);
        notifyItemRemoved(position);
    }
}



