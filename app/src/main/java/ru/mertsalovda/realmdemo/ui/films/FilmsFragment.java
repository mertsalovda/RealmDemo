package ru.mertsalovda.realmdemo.ui.films;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import ru.mertsalovda.realmdemo.R;
import ru.mertsalovda.realmdemo.data.FilmRepositoryImpl;
import ru.mertsalovda.realmdemo.data.model.Film;
import ru.mertsalovda.realmdemo.ui.MainActivity;
import ru.mertsalovda.realmdemo.ui.edit.EditFragment;

public class FilmsFragment extends Fragment implements FilmsView {

    private FilmRepositoryImpl mRepository;

    private FilmsAdapterRealm mFilmsAdapterRealm;

    private FilmsPresenterImpl mFilmsPresenter;

    private RecyclerView mRecyclerView;
    private View mErrorView;

    private EditText mEtTitle;
    private EditText mEtStartYear;
    private EditText mEtEndYear;
    private EditText mEtDirector;
    private EditText mEtTopCount;

    private Button mBtnSearchTitle;
    private Button mBtnSearchInBounds;
    private Button mBtnSearchDirector;
    private Button mBtnSearchTopBest;

    public static FilmsFragment newInstance() {
        return new FilmsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mRepository = new FilmRepositoryImpl();
        mFilmsPresenter = new FilmsPresenterImpl(this, mRepository);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_films, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_films);
        mErrorView = view.findViewById(R.id.v_error);

        mBtnSearchTitle = view.findViewById(R.id.btn_name_search);
        mBtnSearchInBounds = view.findViewById(R.id.btn_year_search);
        mBtnSearchDirector = view.findViewById(R.id.btn_director_search);
        mBtnSearchTopBest = view.findViewById(R.id.btn_top_search);

        mEtTitle = view.findViewById(R.id.et_name_search);
        mEtStartYear = view.findViewById(R.id.et_start_year_search);
        mEtEndYear = view.findViewById(R.id.et_end_year_search);
        mEtDirector = view.findViewById(R.id.et_director_search);
        mEtTopCount = view.findViewById(R.id.et_top_search);

        mBtnSearchTitle.setOnClickListener(v -> {
            String query = mEtTitle.getText().toString();
            mFilmsPresenter.searchForTitle(query);
        });

        mBtnSearchDirector.setOnClickListener(v -> {
            String query = mEtDirector.getText().toString();
            mFilmsPresenter.searchForDirector(query);
        });

        mBtnSearchInBounds.setOnClickListener(v -> searchInBounds());
        mBtnSearchTopBest.setOnClickListener(v -> searchTopBest());

        return view;
    }

    private void searchInBounds() {
        try {
            int start = Integer.valueOf(mEtStartYear.getText().toString());
            int end = Integer.valueOf(mEtEndYear.getText().toString());
            mFilmsPresenter.searchInBounds(start, end);
        } catch (NumberFormatException e) {
            showMessage(R.string.enter_year);
            showError();
        }
    }

    private void searchTopBest() {
        try {
            int count = Integer.valueOf(mEtTopCount.getText().toString());
            mFilmsPresenter.searchRating(count);
        } catch (NumberFormatException e) {
            showMessage(R.string.enter_year);
            showError();
        }
    }

    @Override
    public void showMessage(@StringRes int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFilms(List<Film> films) {
        mFilmsAdapterRealm.addData(films);
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFilmsAdapterRealm = new FilmsAdapterRealm(mRepository.getItemList(),
                true, true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFilmsAdapterRealm);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_10_films:
                mFilmsPresenter.addTestData();
                mFilmsPresenter.getAllFilms();
                return true;
            case R.id.menu_add_one_film:
                MainActivity.startFragment(EditFragment.newInstance(null));
                return true;
            case R.id.menu_show_all_films:
                mFilmsPresenter.getAllFilms();
                return true;
            case R.id.menu_delete_all_films:
                mRepository.deleteAllFilms();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDetach() {
        mRepository = null;
        super.onDetach();
    }
}
