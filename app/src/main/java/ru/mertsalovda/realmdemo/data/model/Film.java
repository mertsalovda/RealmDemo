package ru.mertsalovda.realmdemo.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Film extends RealmObject {

    @PrimaryKey
    private long id;

    private String title;

    private int yearRelease;

    private String director;

    private float rating;

    public Film() {
    }

    public Film(long id, String title, int release, String director, float rating) {
        this.id = id;
        this.title = title;
        this.yearRelease = release;
        this.director = director;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearRelease() {
        return yearRelease;
    }

    public void setYearRelease(int yearRelease) {
        this.yearRelease = yearRelease;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
