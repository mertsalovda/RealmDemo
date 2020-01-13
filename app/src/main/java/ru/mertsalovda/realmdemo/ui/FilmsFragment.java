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
import ru.mertsalovda.realmdemo.data.model.Film;

public class FilmsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FilmsAdapter mFilmsAdapter;

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
        mFilmsAdapter = new FilmsAdapter();
        mFilmsAdapter.addData(getTestData());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFilmsAdapter);
    }

    private List<Film> getTestData() {
        List<Film> result = new ArrayList<>();
        result.add(new Film("Title 0", 1900, "Director 0", 5.5F));
        result.add(new Film("Title 1", 1900, "Director 1", 7.5F));
        result.add(new Film("Title 2", 1900, "Director 2", 5.7F));
        result.add(new Film("Title 3", 1900, "Director 3", 9.5F));
        result.add(new Film("Title 4", 1900, "Director 4", 0.5F));
        result.add(new Film("Title 5", 1900, "Director 5", 1.5F));
        result.add(new Film("Title 6", 1900, "Director 6", 2.5F));
        result.add(new Film("Title 7", 1900, "Director 7", 5.1F));
        result.add(new Film("Title 8", 1900, "Director 8", 7.5F));
        result.add(new Film("Title 9", 1900, "Director 9", 7.5F));
        return result;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
