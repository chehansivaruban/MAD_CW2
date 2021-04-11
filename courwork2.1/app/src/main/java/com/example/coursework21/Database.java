package com.example.coursework21;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    Context context;
    public Database(Context context) {
        super(context, "moviedata.db",null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create Table moviedata" +
                "(title TEXT primary key, year TEXT, director TEXT, actors TEXT, rating TEXT, review TEXT)";
       db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Moviedetails");

    }
    public Boolean insertmoviedata(String title, String year, String director, String actors, String rating, String review){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("year",year);
        contentValues.put("director",director);
        contentValues.put("actors",actors);
        contentValues.put("rating",rating);
        contentValues.put("review",review);
        long result = DB.insert("moviedata",null,contentValues);
        if(result==-1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean editmoviedata(String title, String year, String director, String actors, String rating, String review){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("year",year);
        contentValues.put("director",director);
        contentValues.put("actors",actors);
        contentValues.put("rating",rating);
        contentValues.put("review",review);
        Cursor cursor = DB.rawQuery("Select * from moviedata where title=?",new String[]{title});
        if(cursor.getCount()>0){
        long result = DB.update("moviedata",contentValues,"title=?",new String[] {title});
        if(result==-1){
            return false;
        }else {
            return true;
        }
        }else{
            return false;
        }
    }

    public Boolean deletetmoviedata(String title, String year, String director, String actors, String rating, String review){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from moviedata where title=?",new String[]{title});
        if(cursor.getCount()>0){
            long result = DB.delete("moviedata","title=?",new String[] {title});
            if(result==-1){
                return false;
            }else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from moviedata",null);
       return cursor;
    }
    public ArrayList getTitle(){
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    ArrayList<String> arrayList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from moviedata",null);
        cursor.moveToFirst();
        while (!(cursor.isAfterLast())){
            arrayList.add(cursor.getString((cursor.getColumnIndex("title"))));
            cursor.moveToNext();
        }
        return arrayList;
    }
    public SimpleCursorAdapter populateListViewFromDB(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from moviedata",null);
        SimpleCursorAdapter tileAdapter = (SimpleCursorAdapter) cursor;
        return tileAdapter;

    }
    public Cursor getTitleCursor(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select title from moviedata",null);
        return cursor;
    }
}

