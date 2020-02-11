package com.ahrenswett.classattendance;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

//Adapter needs to extend the RecyclerView.Adapter
//This is done through implementing a ViewHolder that extends the RecyclerView.ViewHolder

class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.BaseViewHolder>{
    //    global vars
    private List<Class> classes;
    private ClassInteractionListener listener;


    public interface ClassInteractionListener{
        void classCommand(Class classSession);
    }


    ClassAdapter(List<Class> classes, ClassInteractionListener listener){
        this.classes = classes;
        this.listener = listener;
    }


     abstract class BaseViewHolder extends RecyclerView.ViewHolder{
        BaseViewHolder(@NonNull View itemView){
            super(itemView);
        }

        abstract void onBind(Class className);
    }



    //    Declare a ViewHolder that declares a listener and the text areas needed and the object
    class ClassViewHolder extends BaseViewHolder implements View.OnClickListener {
        Class myClass;
        TextView classTitleView;
        TextView classSizeView;
        ClassInteractionListener listener;

        ClassViewHolder(@NonNull View itemView,ClassInteractionListener listener) {
            super(itemView);
            this.classTitleView = itemView.findViewById(R.id.classNameText) ;
            this.classSizeView = itemView.findViewById(R.id.classSizeText);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        void onBind(Class className){
            myClass = className;
            classTitleView.setText(className.getClassName());
            classSizeView.setText(String.valueOf(className.getSize()));
        }

        @Override
        public void onClick(View view) {
            listener.classCommand(this.myClass);
        }

    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(MainActivity.TAG, "IN onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_class, parent,false);
        return new ClassViewHolder(v, listener);
    }




    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        Log.i(MainActivity.TAG, "IN onBindViewHolder");
        Class classAtPosition = this.classes.get(position);
        holder.onBind(classAtPosition);
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



