package com.example.coursework21;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        listView = (ListView) findViewById(R.id.favList);
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        myDB = new DatabaseHelper(this);
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getFav();
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(0));
//                ListAdapter listAdapter = new ArrayAdapter<>(this,R.layout.single_item,R.id.list_item,theList);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,theList);
                listView.setAdapter(listAdapter);
            }
            for(int i = 0; i < data.getCount(); i ++ ) {
                listView.setItemChecked(i, true);
            }
        }

    }
}