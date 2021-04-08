package com.example.coursework21;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText title;
    EditText year;
    EditText director;
    EditText act;
    EditText rating;
    EditText review;
    Button save;
    Database DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        title = findViewById(R.id.edit1);
        year = findViewById(R.id.edit2);
        director = findViewById(R.id.edit3);
        act = findViewById(R.id.edit4);
        rating = findViewById(R.id.edit5);
        review = findViewById(R.id.edit6);
        save = findViewById(R.id.savebtn);
        DB = new Database(this);


    }

    public void save(View view) throws Exception {
        String titleTxt = title.getText().toString();
        String yearTxt = year.getText().toString();
        String directorTxt = director.getText().toString();
        String actTxt = act.getText().toString();
        String ratingeTxt = rating.getText().toString();
        String reviewTxt = review.getText().toString();
        try{
        if((0<Integer.parseInt(ratingeTxt) && Integer.parseInt(ratingeTxt)<11) && (Integer.parseInt(yearTxt) > 1895)){
            boolean checkinsertdata = DB.insertmoviedata(titleTxt, yearTxt, directorTxt, actTxt, ratingeTxt, reviewTxt);
            if (checkinsertdata == true) {
                Toast.makeText(RegisterActivity.this, "Movie added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterActivity.this, "Movie not added", Toast.LENGTH_SHORT).show();
            }
        } else{
            if(0>Integer.parseInt(ratingeTxt) || Integer.parseInt(ratingeTxt)>11) {
                //rating.setText("Rating should be between 1 to 10");
                rating.setText("");
                rating.setHint("Rating should be between 1 to 10");
                rating.setHintTextColor(Color.RED);

                Toast.makeText(RegisterActivity.this,"Input error",Toast.LENGTH_SHORT).show();
            }
            if (Integer.parseInt(yearTxt) < 1895) {
                year.setText("");
                year.setHint("Enter an year after 1895");
                year.setHintTextColor(Color.RED);
                Toast.makeText(RegisterActivity.this,"Input error",Toast.LENGTH_SHORT).show();
            }
        }
        }catch (Exception e){

//            if(0>Integer.parseInt(ratingeTxt) || Integer.parseInt(ratingeTxt)>11) {
//                rating.setText("Rating should be between 1 to 10");
//                rating.setTextColor(Color.RED);
//                Toast.makeText(RegisterActivity.this,"Input error",Toast.LENGTH_SHORT).show();
//            }
//            if (Integer.parseInt(yearTxt) < 1895) {
//
//            year.setText("Enter an year after 1895");
//            year.setTextColor(Color.RED);
            Toast.makeText(RegisterActivity.this,"Input error",Toast.LENGTH_SHORT).show();




//        }
    }
        }

}