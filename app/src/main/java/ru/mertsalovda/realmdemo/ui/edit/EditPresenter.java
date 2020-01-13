package ru.mertsalovda.realmdemo.ui.edit;

import ru.mertsalovda.realmdemo.data.model.Film;

public interface EditPresenter {

    void saveFilm(Film film, boolean isUpdate);

    Film getFilm(long id);

}
