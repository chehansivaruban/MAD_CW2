package com.example.coursework21;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditorActivity extends AppCompatActivity {


    TextView titleE;
    TextView year;
    TextView director;
    TextView act;
    TextView review;
    Button update;
    EditText titleEdit;
    EditText yearEdit;
    EditText directorEdit;
    EditText actEdit;
    EditText rateEdit;
    //EditText ratingEdit;
    EditText reviewEdit;
    DatabaseHelper myDB;
    Cursor data;
   // String title;
    String yearStr;
    String directorStr;
    String actStr;
    String rateStr;
    String reviewStr;
    String titleStr;
    RatingBar ratebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        try //hide the tittle bar
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        ArrayList<String> theList = new ArrayList<>();
        myDB = new DatabaseHelper(this);

        String title= getIntent().getStringExtra("title");
        titleStr =title;
        data = myDB.getMovieContents(title);
        titleE = findViewById(R.id.title_ee);
        year = findViewById(R.id.year_ee);
        director = findViewById(R.id.director_ee);
        act = findViewById(R.id.act_ee);
        review = findViewById(R.id.review_ee);
        ratebar = findViewById(R.id.ratingBar);


        titleEdit = findViewById(R.id.edit_name);
        yearEdit = findViewById(R.id.edit_year);
        directorEdit = findViewById(R.id.edit_director);
        actEdit = findViewById(R.id.edit_act);
       // rateEdit = findViewById(R.id.edit_rate);


       // titleE.setText(title);

        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()) {

                yearStr = data.getString(1);
                directorStr = data.getString(2);
                actStr = data.getString(3);
                reviewStr = data.getString(5);
                titleE.setText(data.getString(0));
                year.setText(data.getString(1));
                director.setText(data.getString(2));
                act.setText(data.getString(3));
                review.setText(data.getString(5));
                rateStr = data.getString(4);
                ratebar.setRating(Integer.parseInt(data.getString(4)));



            }

        }

    }

    public void update(View view) {
        String newTitle = titleEdit.getText().toString();
        String newDirector = directorEdit.getText().toString();
        String newYear = yearEdit.getText().toString();
        String newAct = actEdit.getText().toString();
        String newRate = String.valueOf(ratebar.getNumStars());
        String updateTitle = null;
        String updateYear = null;
        String updateDirector = null;
        String updateAct = null;
        String updateRating = null;
        String updateReview = null;
        if (!(newTitle.equals(""))){
            updateTitle= newTitle;
        }else {

                updateTitle = titleStr;

        }

        if (!(newDirector.equals(""))){
            updateDirector= newTitle;
        }else {
                updateDirector = directorStr;

        }

        if (!(newYear.equals(""))){
            updateYear= newTitle;
        }else {
                updateYear = yearStr;
        }
        if (!(newAct.equals(""))){
            updateAct = newAct;
        }else {
            updateAct = actStr;
        }


        if (!(newRate==null)){
            updateRating = newRate;
        }else {
            updateAct = rateStr;
        }


            updateReview =reviewStr;


        System.out.println(titleStr+updateTitle+updateYear+ updateDirector+ updateAct+ updateRating+updateReview);
            myDB.updateMovieData(titleStr,updateTitle, updateYear,  updateDirector, updateAct, "10", updateReview);
    }
}