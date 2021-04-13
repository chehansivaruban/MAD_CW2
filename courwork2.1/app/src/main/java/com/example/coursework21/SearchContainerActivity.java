package com.example.coursework21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mancj.materialsearchbar.MaterialSearchBar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;
import java.util.List;

public class SearchContainerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;

    MaterialSearchBar meterialSearchBar;
    List<String> suggestList = new ArrayList<>();
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_container);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_search);
        layoutManager  = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        meterialSearchBar = (MaterialSearchBar)findViewById(R.id.search_bar);
        myDB = new DatabaseHelper(this);
        meterialSearchBar.setHint("Search");
        meterialSearchBar.setCardViewElevation(10);
        loadSuggestList();
        meterialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for(String search : suggestList){
                    if(search.toLowerCase().contains(meterialSearchBar.getText().toLowerCase())){
                        suggest.add(search);
                    }
                }
                meterialSearchBar.setLastSuggestions(suggest);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        meterialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled){
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        adapter = new SearchAdapter(this,myDB.getMovie());
        recyclerView.setAdapter(adapter);

    }

    private void startSearch(String text) {
        adapter = new SearchAdapter(this,myDB.getMovieByName(text));

        recyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        suggestList = myDB.getMovieNames();
        meterialSearchBar.setLastSuggestions(suggestList);
    }
}