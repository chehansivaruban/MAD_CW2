package com.example.coursework21;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ImdbActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    TextView title;
    TextView rate;
    String finalTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imdb);
        ArrayList<String> theList = new ArrayList<>();
        myDB = new DatabaseHelper(this);

        String titleStr = getIntent().getStringExtra("title");
        title = findViewById(R.id.rate_title);
        rate = findViewById(R.id.rate_rate);
        finalTitle=titleStr;
        title.setText(titleStr);
        new Thread(new ImdbRunnable(titleStr)).start();
    }

    class ImdbRunnable implements Runnable {
        String title_requested;
        String imageUrl=null;

        ImdbRunnable(String title) {
            title_requested = title;
        }

        @Override
        public void run() {
            StringBuilder stb = new StringBuilder("");
            StringBuilder recipe = new StringBuilder("");
            StringBuilder stb1 = new StringBuilder("");
            StringBuilder recipe1 = new StringBuilder("");
            String movieId = null;
            String finalRating= null;


            try {
                // make the connection and receive the input stream
                URL url = new URL("https://imdb-api.com/en/API/SearchTitle/k_dm23y6i5/=" +
                        title_requested.trim());
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                // read all lines in a stringbuilder
                String line;
                while ((line = bf.readLine()) != null) {
                    stb.append(line);
                }

                /* do the JSON parsing */
                JSONObject json = new JSONObject(stb.toString());
                JSONArray jsonArray = json.getJSONArray("results");

                // find the matching cocktail name entry and extract recipe
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json_movie = jsonArray.getJSONObject(i);
                    String imdb_title = json_movie.getString("title");
                    if (imdb_title.toLowerCase().equals(finalTitle.toLowerCase())) {
                        recipe.append(json_movie.getString("id"));
                        movieId=recipe.toString();
                        imageUrl = json_movie.getString("image");
                        System.out.println("abc -"+movieId);
                        System.out.println("abc -"+imageUrl);

                        break;
                    }
                }

                // make the connection and receive the input stream
                URL rateurl = new URL("https://imdb-api.com/en/API/UserRatings/k_dm23y6i5/" +
                        movieId.trim());
                System.out.println(movieId);
                HttpURLConnection con1 = (HttpURLConnection) rateurl.openConnection();

                BufferedReader bf1 = new BufferedReader(new InputStreamReader(con1.getInputStream()));
                // read all lines in a stringbuilder
                String line1;
                while ((line1 = bf1.readLine()) != null) {
                    stb1.append(line1);
                }
                System.out.println(stb1);
                JSONObject json1 = new JSONObject(stb1.toString());
                finalRating = json1.getString("totalRating");
                System.out.println(finalRating);



            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            String finalRating1 = finalRating;
            Bitmap movie_picture = getBitmap();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    JSONObject data = new JSONObject()
                    rate.setText(finalRating1);
                    System.out.println("12-"+recipe);
                    System.out.println("12-"+recipe1);
                    ImageView image = (ImageView) findViewById(R.id.image);
                    image.setImageBitmap(movie_picture);



                  //  System.out.println(jsonArray);

                }
            });
        }
        // retrieve a bitmap image from the URL in JSON
        Bitmap getBitmap() {
            Bitmap bitmap = null;
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                BufferedInputStream bfstream = new BufferedInputStream(con.getInputStream());

                bitmap = BitmapFactory.decodeStream(bfstream);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            return bitmap;
        }
    }
}