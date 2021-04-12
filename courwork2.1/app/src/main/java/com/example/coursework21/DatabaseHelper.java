package com.example.coursework21;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;
import java.util.List;

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

    public Cursor getFav(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("Select * from mylist_data where favourite = ?", new String[]{"1"});
        return data;
    }

    public void addFav(ArrayList<String> fav){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("favourite", 1);
        for (String s:fav){
            Cursor data = db.rawQuery("Select * from mylist_data where title = ?", new String[]{s});
//            long result = db.update(TABLE_NAME,  contentValues,"title =?", new String[]{s});
            if (data.getCount() > 0) {
                long result = db.update("mylist_data", contentValues, "title=?", new String[]{s});
                if (result == -1) {
                    System.out.println("error 1");
                } else {

                    System.out.println("done");
                }
            } else {
                System.out.println("error");
            }
           // System.out.println(s);

        }

    }
    public Boolean updateMovieData(String oldTitle,String title, String year,  String director, String actors, String rating, String review) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("year",year);
        contentValues.put("director",director);
        contentValues.put("actors",actors);
        contentValues.put("rating",rating);
        contentValues.put("review",review);
        //contentValues.put("favourite","0");
        //Cursor cursor = DB.rawQuery("Select * from mylist_data where title = ?", new String[]{oldTitle});

        //if (cursor.getCount() > 0) {
            long result = DB.update("mylist_data", contentValues, "title=?", new String[]{oldTitle});
            if (result == -1) {
                return false;
            } else {
                System.out.println(oldTitle);
                System.out.println("updated");
                return true;
            }
        //} else {
        //    return false;
       // }
    }

    public Cursor getMovieContents(String s){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("Select * from mylist_data where title = ?", new String[]{s});
        System.out.println("[getMovieContents]-"+s);
        return data;
    }

    public List<Movie> getMovie(){
        SQLiteDatabase DB = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"title","year", "director","actors","rating","review","favourite"};
        String tableName = "mylist_data";

        qb.setTables(tableName);
        Cursor cursor = DB.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        List<Movie> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                Movie m = new Movie();
                m.setTitle(cursor.getString(0));
                m.setYear(cursor.getString(1));
                m.setDirector(cursor.getString(2));
                m.setAct(cursor.getString(3));
                m.setRate(cursor.getString(4));
                m.setReview(cursor.getString(5));
                m.setFavourite(cursor.getString(6));
                result.add(m);
            }while (cursor.moveToNext());
        }
        return result;
    }

    public List<String> getMovieNames(){
        SQLiteDatabase DB = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"title"};
        String tableName = "mylist_data";

        qb.setTables(tableName);
        Cursor cursor = DB.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        List<String> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                result.add(cursor.getString(cursor.getColumnIndex("title")));
            }while (cursor.moveToNext());
        }
        return result;
    }

    public List<Movie> getMovieByName(String s){
        SQLiteDatabase DB = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"title","year", "director","actors","rating","review","favourite"};
        String tableName = "mylist_data";

        qb.setTables(tableName);
        Cursor cursor = DB.rawQuery("Select * from mylist_data where title = ?", new String[]{s});
        List<Movie> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                Movie m = new Movie();
                m.setTitle(cursor.getString(0));
                m.setYear(cursor.getString(1));
                m.setDirector(cursor.getString(2));
                m.setAct(cursor.getString(3));
                m.setRate(cursor.getString(4));
                m.setReview(cursor.getString(5));
                m.setFavourite(cursor.getString(6));
                result.add(m);
            }while (cursor.moveToNext());
        }
        return result;
    }
    public void removeFav(ArrayList<String> fav){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("favourite", 0);
        for (String s:fav){
            Cursor data = db.rawQuery("Select * from mylist_data where title = ?", new String[]{s});
//            long result = db.update(TABLE_NAME,  contentValues,"title =?", new String[]{s});
            if (data.getCount() > 0) {
                long result = db.update("mylist_data", contentValues, "title=?", new String[]{s});
                if (result == -1) {
                    System.out.println("error 1");
                } else {

                    System.out.println("done");
                }
            } else {
                System.out.println("error");
            }
            // System.out.println(s);

        }

    }

}