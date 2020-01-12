package ru.mertsalovda.realmdemo.data;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import ru.mertsalovda.realmdemo.data.model.Film;

public class FIlmRepositoryImpl implements FilmRepository {

    private Realm mRealm;
    private static AtomicLong sPrimaryKey;

    public FIlmRepositoryImpl() {
        mRealm = Realm.getDefaultInstance();
        Number max = mRealm.where(Film.class).max("id");
        sPrimaryKey = max == null ? new AtomicLong(0) : new AtomicLong(max.longValue());
    }

    @Override
    public List<Film> getAllFilms() {
        return null;
    }

    @Override
    public Film getAllFilm(long id) {
        return null;
    }

    @Override
    public boolean deleteAllFilms() {
        return false;
    }

    @Override
    public boolean deleteFilm(long id) {
        return false;
    }

    @Override
    public long insertFilm(Film film) {
        return 0;
    }

    @Override
    public void updateFilm(Film film) {

    }

    @Override
    public List<Film> search(String query) {
        return null;
    }

    @Override
    public List<Film> searchInBounds(int startYear, int endYear) {
        return null;
    }

    @Override
    public List<Film> searchByDirector(String name) {
        return null;
    }

    @Override
    public List<Film> getTopFilms(int count) {
        return null;
    }
}
