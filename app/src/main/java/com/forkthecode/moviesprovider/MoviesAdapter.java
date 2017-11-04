package com.forkthecode.moviesprovider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ralph on 04/11/17.
 */

public class MoviesAdapter extends ArrayAdapter<Movie> {

    Context mContext;
    ArrayList<Movie> mItems;

    public MoviesAdapter(Context context, ArrayList<Movie> movies){
        super(context,0);
        mContext = context;
        mItems = movies;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Nullable
    @Override
    public Movie getItem(int position) {
        return mItems.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View output = convertView;
        if(output == null){
            output = LayoutInflater.from(mContext).inflate(R.layout.movie_row,parent,false);
            TextView titleTextView = output.findViewById(R.id.title);
            TextView descTextView = output.findViewById(R.id.description);
            MovieViewHolder holder = new MovieViewHolder();
            holder.title = titleTextView;
            holder.desc = descTextView;
            output.setTag(holder);
        }

        MovieViewHolder holder =(MovieViewHolder) output.getTag();
        Movie movie = getItem(position);
        holder.title.setText(movie.getTitle());
        holder.desc.setText(movie.getDescripion());
        return output;
    }


    class MovieViewHolder {
        TextView title;
        TextView desc;

    }
}
