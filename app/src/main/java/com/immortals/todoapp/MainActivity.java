package com.immortals.todoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btnAdd;

    DatabaseHandler db;
    ArrayList<String> taskID,taskName;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHandler(this);
        taskID = new ArrayList<>();
        taskName = new ArrayList<>();

        storeDataInArray();
        customAdapter = new CustomAdapter(MainActivity.this,this,taskID,taskName);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArray(){
        Cursor cursor =  db.getTasks();
        if(cursor.getCount() == 0){
            Toast.makeText(MainActivity.this, "There is no task available", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()){
                taskID.add(cursor.getString(0));
                taskName.add(cursor.getString(1));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            Toast.makeText(this, "ALL TASK ARE DELETED", Toast.LENGTH_SHORT).show();
            db = new DatabaseHandler(this);
            db.deleteAll();
            recreate();
        }
        return super.onOptionsItemSelected(item);
    }
}