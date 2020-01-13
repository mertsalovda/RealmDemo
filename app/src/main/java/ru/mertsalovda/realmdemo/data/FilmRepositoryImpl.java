package ru.mertsalovda.realmdemo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Case;
import io.realm.ImportFlag;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import ru.mertsalovda.realmdemo.data.model.Film;

public class FilmRepositoryImpl implements FilmRepository {

    private Realm mRealm;
    private static AtomicLong sPrimaryKey;

    public FilmRepositoryImpl() {
        mRealm = Realm.getDefaultInstance();
        Number max = mRealm.where(Film.class).max("id");
        sPrimaryKey = max == null ? new AtomicLong(0) : new AtomicLong(max.longValue());
    }

    @Override
    public List<Film> getAllFilms() {
        return mRealm.where(Film.class).findAll();
    }

    @Override
    public Film getFilm(long id) {
        return mRealm.where(Film.class).equalTo("id", id).findFirst();
    }

    @Override
    public void deleteAllFilms() {
        for (Film film : getAllFilms()) {
            deleteFilm(film.getId());
        }
    }

    @Override
    public boolean deleteFilm(final long id) {
        boolean isDeletedSuccesful = false;
        mRealm.beginTransaction();

        Film film = getFilm(id);

        if (film != null) {
            film.deleteFromRealm();
            isDeletedSuccesful = true;
        }

        mRealm.commitTransaction();

        return isDeletedSuccesful;
    }

    @Override
    public long insertFilm(Film film) {
        film.setId(sPrimaryKey.incrementAndGet());
        mRealm.beginTransaction();
        mRealm.copyToRealm(film, ImportFlag.CHECK_SAME_VALUES_BEFORE_SET);
        mRealm.commitTransaction();

        return sPrimaryKey.longValue();
    }

    @Override
    public long insertAllFilm(List<Film> films) {
        mRealm.beginTransaction();
        for (Film film : films) {
            film.setId(sPrimaryKey.incrementAndGet());
            mRealm.copyToRealm(film, ImportFlag.CHECK_SAME_VALUES_BEFORE_SET);
        }
        mRealm.commitTransaction();

        return sPrimaryKey.longValue();
    }

    @Override
    public void updateFilm(final Film film) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(film, ImportFlag.CHECK_SAME_VALUES_BEFORE_SET);
        mRealm.commitTransaction();
    }

    @Override
    public List<Film> search(String query) {
        RealmResults<Film> films = mRealm.where(Film.class).like("title", "*" + query + "*", Case.INSENSITIVE).findAll();
        return new ArrayList<>(films);
    }

    @Override
    public List<Film> searchInBounds(int startYear, int endYear) {
        RealmResults<Film> films = mRealm.where(Film.class).between("yearRelease", startYear, endYear).findAll();
        return new ArrayList<>(films);
    }

    @Override
    public List<Film> searchByDirector(String name) {
        RealmResults<Film> films = mRealm.where(Film.class).like("director", name + "*", Case.INSENSITIVE).findAll();
        return new ArrayList<>(films);
    }

    @Override
    public List<Film> getTopFilms(int count) {
        return mRealm.where(Film.class).sort("rating", Sort.DESCENDING).limit(count).findAll();
    }
}