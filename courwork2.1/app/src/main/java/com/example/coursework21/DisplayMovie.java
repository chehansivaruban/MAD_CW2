package com.example.coursework21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayMovie extends AppCompatActivity {

    ListView list;
    ArrayList<String> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);
        list.findViewById(R.id.list);
        Database DB = new Database(this);
        movieList = DB.getTitle();
//        movieList = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,DB.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_manu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id
        return super.onOptionsItemSelected(item);
    }
}