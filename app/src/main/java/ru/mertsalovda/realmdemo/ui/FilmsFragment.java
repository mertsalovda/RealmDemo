package ru.mertsalovda.realmdemo.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.mertsalovda.realmdemo.R;
import ru.mertsalovda.realmdemo.data.FilmRepositoryImpl;
import ru.mertsalovda.realmdemo.data.model.Film;

public class FilmsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FilmsAdapter mFilmsAdapter;
    private FilmRepositoryImpl mRepository;

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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRepository = new FilmRepositoryImpl();
        mRepository.deleteAllFilms();

        mFilmsAdapter = new FilmsAdapter();
        List<Film> testData = getTestData();
        mRepository.insertAllFilm(testData);

        mFilmsAdapter.addData(mRepository.searchInBounds(1920, 1950));

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
