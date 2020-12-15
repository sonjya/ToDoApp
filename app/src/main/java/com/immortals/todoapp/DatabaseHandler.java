package com.immortals.todoapp;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbTask";
    private static final String TABLE_NAME = "tblTask";
    private static final String COLUMN_ID = "taskID";
    private static final String COLUMN_TASK = "taskName";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TASK + " TEXT)";
        db.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //adding of task
    public boolean addTask(String data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TASK, data);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    //end of adding task

    //display data
    public Cursor getTasks(){
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(sql,null);
        }

        return cursor;
    }
    //end

    //update tasks
    public boolean updateTask(String rowID,String taskName){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TASK, taskName);
        long result = db.update(TABLE_NAME, cv, "taskID=?", new String[]{rowID});
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }
    //end

    //deleting a task
    public boolean deleteTask(String rowID){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"taskID=?",new String[]{rowID});
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_NAME);
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TASK + " TEXT)";
        db.execSQL(CREATE_ITEM_TABLE);
    }

}
