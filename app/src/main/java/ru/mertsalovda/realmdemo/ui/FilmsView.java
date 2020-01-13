package ru.mertsalovda.realmdemo.ui;

import androidx.annotation.StringRes;

import java.util.List;

import ru.mertsalovda.realmdemo.data.model.Film;

public interface FilmsView {

    void showFilms(List<Film> films);

    void showError();

    void showMessage(@StringRes int message);
}
