package com.example.coursework21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class DisplayMovie extends AppCompatActivity {


    //ArrayAdapter<String> movieList;
    ListView listView;
    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);

        listView = (ListView) findViewById(R.id.listView);

        myDB = new DatabaseHelper(this);


        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{

            while(data.moveToNext()){
                theList.add(data.getString(0));
                Collections.sort(theList);
//                ListAdapter listAdapter = new ArrayAdapter<>(this,R.layout.single_item,R.id.list_item,theList);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,theList);
//                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                listView.setAdapter(listAdapter);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_manu,menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        System.out.println("click");
        int id = item.getItemId();
        ArrayList<String> fav = new ArrayList<String>();
        if (id == R.id.item_done){
            String itemselect = "selected items \n";
            for(int i=0;i<listView.getCount();i++){
                if(listView.isItemChecked(i)){
                    itemselect+=listView.getItemAtPosition(i);
                    fav.add(listView.getItemAtPosition(i).toString());
                }
            }
            Toast.makeText(this, fav+"  Added to Favourite",Toast.LENGTH_LONG).show();
            myDB.addFav(fav);


        }
        return super.onOptionsItemSelected(item);
    }

    public void addFav(View view) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
            }
        });

    }
}