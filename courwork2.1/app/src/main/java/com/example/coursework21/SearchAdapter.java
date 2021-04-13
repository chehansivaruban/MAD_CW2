package com.example.coursework21;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder{

        public TextView title, year, director, act, rate, review ,fav;
    public SearchViewHolder(View itemView){
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.title);
        year = (TextView)itemView.findViewById(R.id.year);
        director = (TextView)itemView.findViewById(R.id.director);
        act = (TextView)itemView.findViewById(R.id.act);
        rate = (TextView)itemView.findViewById(R.id.rate);
        review = (TextView)itemView.findViewById(R.id.review);
        fav = (TextView)itemView.findViewById(R.id.fav);

    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    private List<Movie> movies;

    public SearchAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.activity_search,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.title.setText(movies.get(position).getTitle());
        holder.year.setText(movies.get(position).getYear());
        holder.director.setText(movies.get(position).getDirector());
        holder.act.setText(movies.get(position).getAct());
        holder.rate.setText(movies.get(position).getRate());
        holder.review.setText(movies.get(position).getReview());
        if(movies.get(position).getFavourite().equals(1)){
        holder.fav.setText("Favourite");
                }

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
