package ru.mertsalovda.realmdemo.ui.edit;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.mertsalovda.realmdemo.R;
import ru.mertsalovda.realmdemo.data.FilmRepositoryImpl;
import ru.mertsalovda.realmdemo.data.model.Film;

public class EditFragment extends Fragment implements EditView {


    public static final String IS_UPDATE = "IS_UPDATE";
    public static final String FILM_FOR_UPDATE = "FILM_FOR_UPDATE";
    private boolean mIsUpdate;
    private Film mFilm;

    private FilmRepositoryImpl mRepository;
    private EditPresenter mEditPresenter;

    private EditText mEtTitle;
    private EditText mEtRelease;
    private EditText mEtDirector;
    private EditText mEtRating;
    private Button mBtnSave;

    public static EditFragment newInstance(Film film) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        if (film != null) {
            args.putSerializable(FILM_FOR_UPDATE, film);
            args.putBoolean(IS_UPDATE, true);
        } else {
            args.putBoolean(IS_UPDATE, false);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = new FilmRepositoryImpl();
        mEditPresenter = new EditPresenterImpl(this, mRepository);

        if (getArguments() != null) {
            mIsUpdate = getArguments().getBoolean(IS_UPDATE);
            if (mIsUpdate) {
                mFilm = (Film) getArguments().getSerializable(FILM_FOR_UPDATE);
            } else {
                mFilm = new Film();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_edit, container, false);

        mEtTitle = view.findViewById(R.id.et_title);
        mEtRelease = view.findViewById(R.id.et_release);
        mEtDirector = view.findViewById(R.id.et_director);
        mEtRating = view.findViewById(R.id.et_rating);

        mBtnSave = view.findViewById(R.id.btn_save);

        mBtnSave.setOnClickListener(v -> updateFilm());

        if (mIsUpdate) {
            mEtTitle.setText(mFilm.getTitle());
            mEtRelease.setText(String.valueOf(mFilm.getYearRelease()));
            mEtDirector.setText(mFilm.getDirector());
            mEtRating.setText(String.valueOf(mFilm.getRating()));
        }

        return view;
    }

    private void updateFilm() {
        Film film;
        String title = mEtTitle.getText().toString();
        String director = mEtDirector.getText().toString();
        try {
            int release = Integer.parseInt(mEtRelease.getText().toString());
            float rating = Float.parseFloat(mEtRating.getText().toString());
            film = new Film(title, release, director, rating);
            film.setId(mFilm.getId());
            mEditPresenter.saveFilm(film, mIsUpdate);
        } catch (NumberFormatException e) {
            showMessage(R.string.not_valid_releas_rating);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showMessage(@StringRes int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
