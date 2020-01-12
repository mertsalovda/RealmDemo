package ru.mertsalovda.realmdemo.data;

import java.util.List;

import ru.mertsalovda.realmdemo.data.model.Film;

public interface FilmRepository {

    List<Film> getAllFilms();

    Film getAllFilm(long id);

    boolean deleteAllFilms();

    boolean deleteFilm(long id);

    long insertFilm(Film film);

    void updateFilm(Film film);

    List<Film> search(String query);

    List<Film> searchInBounds(int startYear, int endYear);

    List<Film> searchByDirector(String name);

    List<Film> getTopFilms(int count);
}
