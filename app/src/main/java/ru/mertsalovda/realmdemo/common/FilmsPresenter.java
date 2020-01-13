package ru.mertsalovda.realmdemo.common;

public interface FilmsPresenter {

    void searchForTitle(String query);

    void searchForDirector(String query);

    void searchRating(int count);

    void searchInBounds(int start, int end);

}
