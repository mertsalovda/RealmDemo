package ru.mertsalovda.realmdemo.ui.films;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.mertsalovda.realmdemo.R;
import ru.mertsalovda.realmdemo.data.FilmRepositoryImpl;
import ru.mertsalovda.realmdemo.data.model.Film;
import ru.mertsalovda.realmdemo.ui.MainActivity;
import ru.mertsalovda.realmdemo.ui.edit.EditFragment;

public class FilmHolder extends RecyclerView.ViewHolder implements PopupMenu.OnMenuItemClickListener {

    private View mView;
    private TextView mTitle;
    private TextView mDirector;
    private TextView mRelease;
    private TextView mRating;
    private Film mFilm;

    public FilmHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mTitle = mView.findViewById(R.id.tv_title_film);
        mDirector = mView.findViewById(R.id.tv_director_film);
        mRelease = mView.findViewById(R.id.tv_year_release_film);
        mRating = mView.findViewById(R.id.tv_rating_film);
        itemView.setOnClickListener(view -> {
            PopupMenu popup = new PopupMenu(view.getContext(), view);
            popup.inflate(R.menu.edit_menu);
            popup.setOnMenuItemClickListener(FilmHolder.this);
            popup.show();
        });
    }

    public void bind(Film film) {
        mFilm = film;
        mTitle.setText(film.getTitle());
        mDirector.setText(film.getDirector());
        mRelease.setText(String.valueOf(film.getYearRelease()));
        mRating.setText(String.valueOf(film.getRating()));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_update_film:
                MainActivity.startFragment(EditFragment.newInstance(mFilm));
                return true;
            case R.id.menu_delete_film:
                new FilmRepositoryImpl().deleteFilm(mFilm.getId());
                return true;
            default:
                return false;
        }
    }
}
