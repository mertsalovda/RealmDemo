package ru.mertsalovda.realmdemo.ui.films;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import ru.mertsalovda.realmdemo.R;
import ru.mertsalovda.realmdemo.common.FilmsPresenter;
import ru.mertsalovda.realmdemo.data.FilmRepositoryImpl;
import ru.mertsalovda.realmdemo.data.model.Film;

public class FilmsPresenterImpl implements FilmsPresenter {

    private FilmRepositoryImpl mRepository;
    private FilmsView mView;

    public FilmsPresenterImpl(FilmsView view, FilmRepositoryImpl repository) {
        mRepository = repository;
        mView = view;
    }

    @Override
    public void searchForTitle(String query) {
        if (!isValidQueryTitleSearch(query)) {
            mView.showMessage(R.string.valid_text_3);
            mView.showError();
        } else {
            List<Film> films = mRepository.search(query);
            mView.showFilms(films);
            if (films.isEmpty())
                mView.showError();
        }
    }

    @Override
    public void searchForDirector(String query) {
        if (!isValidQueryDirectorSearch(query)) {
            mView.showMessage(R.string.valid_text_4);
            mView.showError();
        } else {
            List<Film> films = mRepository.searchByDirector(query);
            mView.showFilms(films);
            if (films.isEmpty())
                mView.showError();
        }
    }

    @Override
    public void searchRating(int count) {
        List<Film> films = mRepository.getTopFilms(count);
        if (films.isEmpty()) mView.showError();
        else mView.showFilms(films);
    }

    @Override
    public void searchInBounds(int start, int end) {
        List<Film> films = mRepository.searchInBounds(start, end);
        if (films.isEmpty()) mView.showError();
        else mView.showFilms(films);
    }

    @Override
    public void getAllFilms() {
        mView.showFilms(mRepository.getAllFilms());
    }

    @Override
    public void addTestData() {
        List<Film> testData = getTestData();
        mRepository.insertAllFilm(testData);
    }

    private boolean isValidQueryTitleSearch(String query) {
        return !TextUtils.isEmpty(query) && query.length() >= 3;
    }

    private boolean isValidQueryDirectorSearch(String query) {
        return !TextUtils.isEmpty(query) && query.length() >= 4;
    }

    private List<Film> getTestData() {
        List<Film> result = new ArrayList<>();
        result.add(new Film("Title 0", 1900, "Director 0", 5.5F));
        result.add(new Film("Title 1", 1910, "Director 1", 7.5F));
        result.add(new Film("Title 2", 1920, "Director 2", 5.7F));
        result.add(new Film("Title 3", 1930, "Director 3", 9.5F));
        result.add(new Film("mTitle 4", 1940, "Director 4", 0.5F));
        result.add(new Film("mTitle 5", 1950, "Director 5", 1.5F));
        result.add(new Film("mTitle 6", 1960, "Director 6", 2.5F));
        result.add(new Film("mTitle 7", 1970, "Director 7", 5.1F));
        result.add(new Film("mTitle 8", 1980, "Director 8", 7.5F));
        result.add(new Film("mTitle 9", 1990, "Director 9", 7.5F));
        return result;
    }
}
