package ru.mertsalovda.realmdemo.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.mertsalovda.realmdemo.R;
import ru.mertsalovda.realmdemo.data.model.Film;

public class FilmHolder extends RecyclerView.ViewHolder {

    private View mView;
    private TextView mTitle;
    private TextView mDirector;
    private TextView mRelease;
    private TextView mRating;

    public FilmHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mTitle = mView.findViewById(R.id.tv_title_film);
        mDirector = mView.findViewById(R.id.tv_director_film);
        mRelease = mView.findViewById(R.id.tv_year_release_film);
        mRating = mView.findViewById(R.id.tv_rating_film);
    }

    public void bind( Film film){
            mTitle.setText(film.getTitle());
            mDirector.setText(film.getDirector());
            mRelease.setText(String.valueOf(film.getYearRelease()));
            mRating.setText(String.valueOf(film.getRating()));
    }
}
