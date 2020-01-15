package ru.mertsalovda.realmdemo.ui.films;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import ru.mertsalovda.realmdemo.R;
import ru.mertsalovda.realmdemo.data.model.Film;

public class FilmsAdapterRealm extends RealmRecyclerViewAdapter<Film, FilmHolder> {


    public FilmsAdapterRealm(@Nullable OrderedRealmCollection<Film> data, boolean autoUpdate, boolean updateOnModification) {
        super(data, autoUpdate, updateOnModification);
    }

    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_film, parent, false);
        return new FilmHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmHolder holder, int position) {
        holder.bind(getData().get(position));
    }

    public void addData(List<Film> films){
        updateData((OrderedRealmCollection<Film>)films);
    }

    @Override
    public void updateData(@Nullable OrderedRealmCollection<Film> data) {
        super.updateData(data);
    }
}
