package com.immortals.todoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.LayoutInflaterFactory;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList taskID, taskName;
    Activity activity;

    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList taskID,
                  ArrayList taskName){
        this.activity = activity;
        this.context = context;
        this.taskID = taskID;
        this.taskName = taskName;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtID.setText(String.valueOf(taskID.get(position)));
        holder.txtTask.setText(String.valueOf(taskName.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(taskID.get(position)));
                intent.putExtra("task", String.valueOf(taskName.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtID, txtTask;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.txtID);
            txtTask = itemView.findViewById(R.id.txtTask);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
