package ru.mertsalovda.realmdemo.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.mertsalovda.realmdemo.R;
import ru.mertsalovda.realmdemo.data.FilmRepositoryImpl;
import ru.mertsalovda.realmdemo.data.model.Film;

public class FilmsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FilmsAdapter mFilmsAdapter;
    private FilmRepositoryImpl mRepository;

    private EditText mEtTitle;
    private EditText mEtStartYear;
    private EditText mEtEndYear;
    private EditText mEtDirector;
    private EditText mEtTopCount;

    private Button mBtnSearchTitle;
    private Button mBtnSearchInBounds;
    private Button mBtnSearchDirecrot;
    private Button mBtnSearchTopBest;

    public static FilmsFragment newInstance() {
        return new FilmsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_films, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_films);

        mBtnSearchTitle = view.findViewById(R.id.btn_name_search);
        mBtnSearchInBounds = view.findViewById(R.id.btn_year_search);
        mBtnSearchDirecrot = view.findViewById(R.id.btn_director_search);
        mBtnSearchTopBest = view.findViewById(R.id.btn_top_search);

        mEtTitle = view.findViewById(R.id.et_name_search);
        mEtStartYear = view.findViewById(R.id.et_start_year_search);
        mEtEndYear = view.findViewById(R.id.et_end_year_search);
        mEtDirector = view.findViewById(R.id.et_director_search);
        mEtTopCount = view.findViewById(R.id.et_top_search);

        mBtnSearchTitle.setOnClickListener(v -> {
            List<Film> films = new ArrayList<>();
            String query = mEtTitle.getText().toString();
            if (!isValidQueryTitleSearch(query)) {
                showMessage(R.string.valid_text_3);
            } else {
                films = mRepository.search(query);
            }
            mFilmsAdapter.addData(films, true);
        });

        mBtnSearchInBounds.setOnClickListener(v -> {
            try {
                int start = Integer.valueOf(mEtStartYear.getText().toString());
                int end = Integer.valueOf(mEtEndYear.getText().toString());
                List<Film> films = mRepository.searchInBounds(start, end);
                mFilmsAdapter.addData(films, true);
            } catch (NumberFormatException e) {
                showMessage(R.string.enter_year);
            }
        });

        mBtnSearchDirecrot.setOnClickListener(v -> {
            List<Film> films = new ArrayList<>();
            String query = mEtDirector.getText().toString();
            if (!isValidQueryDirectorSearch(query)) {
                showMessage(R.string.valid_text_4);
            } else {
                films = mRepository.searchByDirector(query);
            }
            mFilmsAdapter.addData(films, true);
        });

        mBtnSearchTopBest.setOnClickListener(v -> {
            try {
                int count = Integer.valueOf(mEtTopCount.getText().toString());
                List<Film> films = mRepository.getTopFilms(count);
                mFilmsAdapter.addData(films, true);
            } catch (NumberFormatException e) {
                showMessage(R.string.enter_year);
            }
        });

        return view;
    }

    private void showMessage(@StringRes int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private boolean isValidQueryTitleSearch(String query) {
        return !TextUtils.isEmpty(query) && query.length() >= 3;
    }

    private boolean isValidQueryDirectorSearch(String query) {
        return !TextUtils.isEmpty(query) && query.length() >= 4;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRepository = new FilmRepositoryImpl();
        mRepository.deleteAllFilms();


        List<Film> testData = getTestData();
        mRepository.insertAllFilm(testData);

        mFilmsAdapter = new FilmsAdapter();
        mFilmsAdapter.addData(testData, true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFilmsAdapter);
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        mRepository = null;
        super.onDetach();
    }
}
