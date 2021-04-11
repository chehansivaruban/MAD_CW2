package com.example.coursework21;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mylist.db";
    public static final String TABLE_NAME = "mylist_data";
    public static final String COL1 = "titel";
    public static final String COL2 = "year";
    public static final String COL3 = "director";
    public static final String COL4 = "actors";
    public static final String COL5 = "rating";
    public static final String COL6 = "review";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (title TEXT primary key, year TEXT, director TEXT, actors TEXT, rating TEXT, review TEXT,favourite TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String title, String year, String director, String actors, String rating, String review) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("year",year);
        contentValues.put("director",director);
        contentValues.put("actors",actors);
        contentValues.put("rating",rating);
        contentValues.put("review",review);
        contentValues.put("favourite","0");
        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public void addFav(ArrayList<String> fav){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("favourite", 1);
        for (String s:fav){
            Cursor data = db.rawQuery("Select favourite from " + TABLE_NAME+ "where title ="+ s+";", null);
            long result = db.update(TABLE_NAME,  contentValues,"title =?", new String[]{s});
            System.out.println(s);

        }

    }
    public Boolean updateuserdata(String name, String contact, String dob) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}

}