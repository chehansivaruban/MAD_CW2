package com.example.coursework21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =MainActivity.class.getSimpleName(); //log message

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try //hide the tittle bar
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

    }
    public void launchRegister(View view) {
        Log.d(LOG_TAG, "Button one clicked!"); //give log message
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }
    public void launchDisplay(View view) {
        Log.d(LOG_TAG, "Button 2 clicked!"); //give log message
        Intent intent = new Intent(this, DisplayMovie.class);
        startActivity(intent);

    }

    public void launchFav(View view) {
        Log.d(LOG_TAG, "Button 3 clicked!"); //give log message
        Intent intent = new Intent(this, FavouriteActivity.class);
        startActivity(intent);
    }

    public void launchEdit(View view) {
        Log.d(LOG_TAG, "Button 4 clicked!"); //give log message
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    public void launchSearch(View view) {
        Log.d(LOG_TAG, "Button 5 clicked!"); //give log message
        Intent intent = new Intent(this, SearchContainerActivity.class);
        startActivity(intent);
    }
}