package ru.mertsalovda.realmdemo.data;

import java.util.List;

import ru.mertsalovda.realmdemo.data.model.Film;

public interface FilmRepository {

    List<Film> getAllFilms();

    Film getFilm(long id);

    void deleteAllFilms();

    boolean deleteFilm(final long id);

    long insertFilm(Film film);

    long insertAllFilm(List<Film> film);

    void updateFilm(Film film);

    List<Film> search(String query);

    List<Film> searchInBounds(int startYear, int endYear);

    List<Film> searchByDirector(String name);

    List<Film> getTopFilms(int count);
}
