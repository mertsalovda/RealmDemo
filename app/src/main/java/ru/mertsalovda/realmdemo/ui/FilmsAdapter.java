package ru.mertsalovda.realmdemo.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.mertsalovda.realmdemo.R;
import ru.mertsalovda.realmdemo.data.model.Film;

public class FilmsAdapter extends RecyclerView.Adapter<FilmHolder> {

    private List<Film> mFilms = new ArrayList<>();

    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_film, parent, false);
        return new FilmHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmHolder holder, int position) {
        holder.bind(mFilms.get(position));
    }

    @Override
    public int getItemCount() {
        return mFilms.size();
    }

    public void addData(List<Film> films, boolean clear){
        if (clear){
            mFilms.clear();
        }
        mFilms.addAll(films);
        notifyDataSetChanged();
    }
}
