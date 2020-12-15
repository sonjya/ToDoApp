package com.immortals.todoapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText taskName;
    Button updatebutton,deletebutton;

    DatabaseHandler db;

    String id,task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        taskName = findViewById(R.id.txtTaskNameUpdate);

        getAndSetIntentData();

        ActionBar ab =  getSupportActionBar();
        if(ab != null){
            ab.setTitle(task);
        }

        updatebutton = findViewById(R.id.btnUpdate);
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db =  new DatabaseHandler(UpdateActivity.this);
                task = taskName.getText().toString().trim();
                boolean updated = db.updateTask(id,task);
                if(updated == true){
                    Toast.makeText(UpdateActivity.this, "TASK UPDATED!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "ERROR UPDATING", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        deletebutton = findViewById(R.id.btnDelete);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db =  new DatabaseHandler(UpdateActivity.this);
                boolean deleted = db.deleteTask(id);
                finish();
                if(deleted == true){
                    Toast.makeText(UpdateActivity.this, "TASK COMPLETED!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "ERROR DELETING", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("task")){
            //getting data from intent
            id = getIntent().getStringExtra("id");
            task = getIntent().getStringExtra("task");

            //setting intent data
            taskName.setText(task);
        } else {
            Toast.makeText(UpdateActivity.this, "NO DATA FETCHED", Toast.LENGTH_SHORT).show();
        }
    }
}