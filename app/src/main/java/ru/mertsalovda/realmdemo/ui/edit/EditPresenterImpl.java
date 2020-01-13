package ru.mertsalovda.realmdemo.ui.edit;

import ru.mertsalovda.realmdemo.R;
import ru.mertsalovda.realmdemo.data.FilmRepositoryImpl;
import ru.mertsalovda.realmdemo.data.model.Film;

public class EditPresenterImpl implements EditPresenter {

    private EditView mView;
    private FilmRepositoryImpl mRepository;

    public EditPresenterImpl(EditView view, FilmRepositoryImpl repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void saveFilm(Film film, boolean isUpdate) {
        if (isUpdate) {
            mRepository.updateFilm(film);
            mView.showMessage(R.string.succesful);
        } else {
            long l = mRepository.insertFilm(film);
            if (l >= 0) {
                mView.showMessage(R.string.succesful);
            }
        }
    }

    @Override
    public Film getFilm(long id) {
        return mRepository.getFilm(id);
    }
}
