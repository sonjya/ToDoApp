package com.immortals.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    DatabaseHandler db;
    Button add;
    EditText txtTaskName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db = new DatabaseHandler(this);

        txtTaskName = findViewById(R.id.txtTaskNameUpdate);
        add = findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(txtTaskName.length() != 0){
                    boolean isadded = db.addTask(txtTaskName.getText().toString());
                    if(isadded == true){
                        Toast.makeText(AddActivity.this, "TASK ADDED", Toast.LENGTH_SHORT).show();
                        txtTaskName.setText("");
                    }else {
                        Toast.makeText(AddActivity.this, "ERROR ADDING", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddActivity.this, "EMPTY FIELD!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}